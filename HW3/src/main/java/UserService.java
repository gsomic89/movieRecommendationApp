import com.netflix.hystrix.*;

public class UserService {
    private User AuthUser = new User("Goran", 30);


    public int getAge() throws Exception{
        return AuthUser.getAge();
    }


}