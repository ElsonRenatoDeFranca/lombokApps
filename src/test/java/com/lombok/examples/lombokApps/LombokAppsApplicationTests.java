package com.lombok.examples.lombokApps;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

@SpringBootTest
class LombokAppsApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void constructor_shouldReturnCustomerInstance_WhenRequiredArgsConstructorAnnotationIsUsed(){
		Customer expected = new Customer("Pele",80,"Santos");

		assertThat(expected, is(notNullValue()));
	}

    //[FEATURE] "Mandatory" fields with @Builder #2630 -Set @NonNull as Null
    @Test
    public void constructor_shouldReturnCustomerInstance_WhenRequiredArgsConstructor_and_nonNullFieldsAreSetAsEmptyString(){
        Customer expected = new Customer("",80,"Santos");
        assertThat(expected, is(notNullValue()));
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

	@Test
	public void toString_shouldReturnOnlyNameAttribute_WhenOptionExcludeIsApplied(){
		Developer developer = Developer.builder()
				.name("Dona Florinda")
				.language("Kotlin")
				.salary(20000)
				.build();

		String str = developer.toString();

		assertThat(str, is(not(nullValue())));
		assertThat(str, containsString("Dona Florinda"));
	}






}
