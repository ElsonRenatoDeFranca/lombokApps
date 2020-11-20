# lombokApps

Currently the Lombok project doesn't support the abstraction of creating an object instance with mandatory/optional fields. So if we come accross a situation where we need to define @MandatoryField or @OptionalField annotations, we need to emulate such a scenario by following an approach according to steps below:

1- Use the @RequiredArgsConstructor annoation and decorate all non null properties by using the @NonNull annotation;

    @Getter
    @Setter
    @RequiredArgsConstructor
    public class Customer {
       private String id;
       @NonNull private String name;
       @NonNull private Integer age;
       @NonNull private String address;
    }
 
 This can be translated into Vanilla Java like this
 
    public class Customer {
        private String id;
        private String name;
        private Integer age;
        private String address;
       
        public Customer (String id){
	   this.id = id;
	}
	
        public Customer(String name) {
           if (description == null) throw new NullPointerException("Name is null but it was supposed to be not null");
                 this.description = description;
         }
 
        public Customer(Integer age) {
           if (age == null) throw new NullPointerException("Age is null but it was supposed to be not null");
                this.age = age;
        }
 
        public Customer(String address) {
           if (address == null) throw new NullPointerException("Address is null but it was supposed to be not null");
               this.address = address;
        }
     }
    
   
  The code snippet above will throw a Null Pointer Exception. 
  
       @Test
       public void constructor_shouldReturnCustomerInstance_WhenRequiredArgsConstructorAnnotationIsUsed(){
		Customer expected = new Customer(null,80,"Santos");
		assertThat(expected, is(notNullValue()));
       }
  
  The workaround for that is to use "" an empty string instead of null.
  
      @Test
      public void constructor_shouldReturnCustomerInstance_WhenRequiredArgsConstructor_and_nonNullFieldsAreSetAsEmptyString(){
          Customer expected = new Customer("",80,"Santos");
          assertThat(expected, is(notNullValue()));
      }

  
  2- The second approach is to use the @Builder annotation that implements the Builder design pattern that The Builder pattern allows us to write readable, understandable code to set up complex objects:
  
  
    @Getter
    @Setter
    @Builder
    public class Person {
        private String id;
        private String name;
        private Integer age;
        private String address;
    }

     @Test
     public void constructor_shouldReturnCustomerInstance_whenNameAndAgeArgumentsArePassed(){
		Person personBuilder = Person.builder()
				.name("Seu Madruga")
				.age(40)
				.build();

		assertThat(personBuilder, hasProperty("name", is(equalTo("Seu Madruga"))));
		assertThat(personBuilder, hasProperty("age", is(equalTo(40))));
    }



3- A similar scenario can be found when we need to prevent some fields from being exposed by toString() method. By default, Lombok will
display all attributes unless you tell it to do another way.

    
    @Getter
    @Setter
    @ToString(exclude = {"id", "language"})
    @Builder
    public class Developer {
       private String id;
       private String name;
       private String language;
    }
  

    @Test
    public void toString_shouldReturnOnlyNameAttribute_WhenOptionExcludeIsApplied(){
		Developer developer = Developer.builder()
				.name("Dona Florinda")
				.language("Kotlin")
				.id("20000")
				.build();

		String str = developer.toString();

		assertThat(str, is(not(nullValue())));
		assertThat(str, containsString("Dona Florinda"));
	}
