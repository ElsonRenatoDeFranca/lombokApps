# lombokApps

Currently the Lombok project doesn't support the abstraction of creating an object instance with mandatory/optional fields. This idea is still around in Lombok community but we 
still don't have such an @MandatoryField or @OptionalField annotation. If we want to emulate such a scenario we need to do it according the steps described below:

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
 
 Testing this approach 
	
  @Test
	public void constructor_shouldReturnCustomerInstance_WhenRequiredArgsConstructorAnnotationIsUsed(){
		Customer expected = new Customer(null,80,"Santos");

		assertThat(expected, is(notNullValue()));
	}
  
  The code snippet above will throw a Null Pointer Exception. 
  
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


 

  
