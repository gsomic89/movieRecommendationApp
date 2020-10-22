import com.netflix.hystrix.*;

public class MovieService extends HystrixCommand<String> {
    UserService userService;
    public MovieService(UserService _userService) {
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("SystemX"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("PrimaryCommand"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("PrimaryCommand"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(100)));
                this.userService = _userService;
    }


    @Override
    protected String run() throws Exception {
        int userAge = this.userService.getAge();



        String pg = "Shrek, Coco, The Incredibles";
        String pg13 = "The Avengers, The Dark Knight, Inception";
        String ratedR = "The Godfather, Deadpool, Saving Private Ryan";

        if(userAge < 13){
            return pg;
        } else if(userAge > 13 && userAge < 17){
            return pg13;
        } else {
            return ratedR;
        }
    }
    @Override
    protected String getFallback() {
        return "Shrek, Coco, The Incredibles";
    }

}
