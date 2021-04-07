## Jasypt Encryptor Demo

The demo project shows how **Jasypt** could be embeded in our springboot application. The overall structure  of this demo project is similar with our real project.  Below are the major steps you may want to follow to add the jasypt encrptor in any springboot application.



### Step 1: Add POM dependency

Before you start to explore Jasypt, Simply add the starter jar dependency to your **POM.xml** if your Spring Boot application uses `@SpringBootApplication` or `@EnableAutoConfiguration` and encryptable properties will be enabled across the entire Spring Environment (This means any system property, environment property, command line argument, application.properties, yaml properties, and any other custom property sources can contain encrypted properties):

```xml
<dependency>
       <groupId>com.github.ulisesbocchio</groupId>
       <artifactId>jasypt-spring-boot-starter</artifactId>
       <version>3.0.3</version>
</dependency>
```



### Step 2: Define your custom encryptor

For security concerns, we may want to define our custom encryptor rather than use the default one. An custom encrytor could be defined as  below:

```Java
@Configuration
public class CustomJasyptEncryptor {
    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor(){
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("password");
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }
}

```

As shown in the code above, you can customize the jasypt password (also known as encryption salt, not the database password), encrypt algorithm and other settings. 

The default bean name is `jasyptStringEncryptor` , you don't have to specify the bean in application.yml if this default name is used. 

Alternatively, you could choose to override this property. 

```
jasypt.encryptor.bean
```

So for instance, if you define `jasypt.encryptor.bean=encryptorBean` then you would define your custom encryptor with that name:

```Java
@Bean("encryptroBean")
```



### Step 3: Obtain the encrypted String

There are mutiple approaches to obtain the encrypted string. In this demo project, an encryptUtil is defined as below.

```Java
@Component
public class EncryptUtil {

    @Autowired
    CustomJasyptEncryptor customJasyptEncryptor;

    public String encryptString(String plainText){
        StringEncryptor encryptor = customJasyptEncryptor.stringEncryptor();
        return encryptor.encrypt(plainText);
    }

    public String decryptString(String encryptedString){
        StringEncryptor encryptor = customJasyptEncryptor.stringEncryptor();
        return encryptor.decrypt(encryptedString);
    }
}
```

Then you could obatin the encrypted string using any runnable thread. (SpringbootTest is used in my demo)

```Java
@SpringBootTest
class SpringjpademoApplicationTests {
    @Autowired
    EncryptUtil util;

    @Test
    void EncryptorTest() {
        System.out.println(util.encryptString("db_username"));
        System.out.println(util.encryptString("db_password"));
    }

}
```

**You could also use the online tool or command line tool provided by Jasypt to generate the encrypted string.**

### Step 4: Put the encrpyted string to the configuration file.

Once you obtain the encrypted string from the last step, you have to put them to the configuration file(i.e. application.yml). 

```yml
countryDBDetails:
  - countryCode: "TW"
    url: "jdbc:mysql://localhost:3306/SPRING_DEMO"
    username: "ENC(encryptedUsername)"
    password: "ENC(encryptedPassword)"
```

**Note:** the encryted username and password should be put in the "ENC()" bracket, as only in this case they could be identified by the jasypt. 