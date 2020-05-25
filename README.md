# Validator

该文档和源码通过B站 https://www.bilibili.com/video/BV1UE411t7BZ 学习而来。UP主UID：51950540



#### 主要的验证方式 

1. ##### 传统的参数校验方式 （if else，这个没啥可说的）

   

2. ##### 开始使用validator

   在验证的类上加 **@Validated** 注解，在需要校验的地方加常用的 **@NotNull** 等,注意，这些注解不是只可以放在类的属性中，也可以针对单个参数进行校验，也可以对返回值进行校验！

   有两个需要注意的地方，一些其他的注解不会对null值进行校验， 例如**@Size**，如果传入null值可以通过校验，这里需要同时加上**@NotNull**进行非空的校验。为什么是这样，因为源码中写的，自定义注解可以控制

   同一个注解，可以对多个类型的参数进行校验，如**@Size**可以对List和String等进行校验，因为每一个注解所支持的验证的类型，都有一个注解的具体实现类类进行处理

   ```java
   //单个属性的校验和返回值校验
   @Override
   public @NotNull Employee findById(@NotNull Integer id) {
       return null;
   }
   ```

   

3. ##### 级联验证（1对1，1对多）（在实体属性中加上校验注解）

   ```java
   @Data
   public class Employee {
       @Valid
       private Department department;
   }
   
   @Data
   public class Department {
       private List<@Valid Employee> employees;
   }
   
   ```

   

4. ##### 在service中做参数验证（因为有可能会使用RPC，在多个controller中做校验,这样不如在service中做校验方便）

   - service一般有常用的两种写法， 一种是面向接口，一种是只有一个service类

   - 只有一个service类的写法： 和在controller没啥区别，就是将 **@Validated** 注解直接加到service类上，在service类的方法参数中增加 **@Valid** 即可

   ```java
   //只有一个service类的写法
   @Service
   @Validated
   public class DepartmentService {
   
       public void add(@Valid Department department) {
           //业务逻辑
       }
   }
   ```

   - 面向接口的service写法有点不同，@Validated 如果加在接口上，那么实现类都需要进行校验，针对这种情况，如果不是每个实现类都要加校验，推荐将@Validated注解加在实现类上，更灵活一些。 接下来的是@Valid注解，在面向接口的时候，这个注解要放在接口定义的方法上，不然会报错的！～

   ```java
   public interface IemployeeService {
   
       void add(@Valid Employee employee);
   
       Employee findById(@NotNull Integer id);
   }
   ```

   - 这里有一个关于异常的处理需要注意一下：在统一异常处理的时候，在controller进行校验并且不通过，抛出的异常类是MethodArgumentNotValidException， 在service抛出的是ConstraintViolationException，注意处理时根据不同的需求进行不同的处理！
   - 

   ```java
   @RestControllerAdvice(basePackages = "cn.zhangjl.validator.controller")
   public class MyControllerAdvice {
   
       @ExceptionHandler
       public ResultVo exceptionHandler(MethodArgumentNotValidException e) {
           Map<String, String> collect = e.getBindingResult().getFieldErrors().stream()
                   .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
           return ResultVo.fail(ErrorCode.PARAM_ERROR, collect);
       }
   
       @ExceptionHandler
       public ResultVo exceptionHandler(ConstraintViolationException e) {
           Map<Path, String> collect = e.getConstraintViolations().stream()
                   .collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage));
           return ResultVo.fail(ErrorCode.PARAM_ERROR, collect);
       }
   }
   ```

   

5. ##### 分组验证（新增和修改时使用同一个对象，但是需要校验的方式不同）

   - 举例场景，当新增时一般后台生成ID，如mysql自增主键或者雪花算法等等，那么新增的时候就不需要前端传入主键，但是如果修改，就必须的传入带有唯一性质的，一般由主键来进行对象属性的修改，那么这样就会出现同一个验证，不能满足两种场景的需求，但是也不能只因为这种小问题而加入一个新的vo，所以这时候使用分组验证，就是一个很合适的方式了。

   - 分组验证主要的区别，在于在类的属性上加的验证注解，增加一个**groups**参数，来指定该验证格式属于哪个组，如果没有指定，属于默认组，一个参数可以属于多个组，在{}中使用逗号分隔。

     ```
     @Data
     public class Employee {
     
         /**
          * 如果指定了验证的组，那么该参数只会属于指定的组
          * 如果没有指定组， 那么属于默认组，需要在校验时同时加上默认组 javax.validation.groups.Default.class
          */
         public interface Add {
         }
     
         public interface Modify {
         }
     
         @Null(groups = {Add.class})       // 在添加时生效
         @NotNull(groups = {Modify.class}) // 在修改时生效
         private Integer id;
     
     }
     ```

   - 那么在接口中就需要指定需要验证的组，因为 **@Valid**  没有参数，所以使用的是 **@Validated**，使用value参数来指定需要验证的组，可以是多个，{}中使用逗号分隔。

     ```
      @PostMapping
         public ResultVo add(@RequestBody @Validated(value = {Employee.Add.class, Default.class}) Employee employee) {
             return ResultVo.success();
         }
     
         @PutMapping
         public ResultVo modify(@RequestBody @Validated(value = {Employee.Modify.class, Default.class}) Employee employee) {
             return ResultVo.success();
         }
     ```

     

6. ##### 自定义注解

   场景举例：

   	1. 对于Integer而言，必须是3的倍数
    	2. 对于List而言，list中的元素个数，必须是3的倍数
    	3. 自定义注解实现 3 的倍数的校验，该注解支持两种数据类型，Integer和List

   实现步骤：

   1. 自定义注解,可以直接参照其他的注解创建

      ```java
      @Target({FIELD})
      @Retention(RUNTIME)
      @Documented
      @Constraint(validatedBy = {MultipleOfThreeForInteger.class, MultipleOfThreeForList.class})
      public @interface MultipleOfThree {
      
          String message() default "必须是3的倍数";
      
          Class<?>[] groups() default {};
      
          Class<? extends Payload>[] payload() default {};
      
      }
      ```

   2. 编写实现类，具体的实现，需要实现ConstraintValidator接口，泛型参数1为定义的注解，泛型参数二为对什么属性进行校验

      ```
      public class MultipleOfThreeForInteger implements ConstraintValidator<MultipleOfThree, Integer> {
      
          @Override
          public void initialize(MultipleOfThree constraintAnnotation) {
          }
      
          @Override
          public boolean isValid(Integer value, ConstraintValidatorContext context) {
              //不对null的值生效， 这个是默认的根据其他的规则来的，可以自己去进行定义，这样不容易搞乱
              if (value == null) {
                  return true;
              }
              return value % 3 == 0;
          }
      }
      ```

   3. 

7. ##### List中做分组校验

   

8. ##### Bean参数间的逻辑校验（自己设计）



| 标准 | jsr303 | jsr-349 | jsr380 | validator-api 提供接口       |
| ---- | ------ | ------- | ------ | ---------------------------- |
| 版本 | 1.0    | 1.1     | 2.0    | hibernate-validator 常用实现 |



