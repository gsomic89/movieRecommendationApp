import org.junit.jupiter.api.BeforeEach;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieServiceTest {

    MovieService movieService;
    UserService userService;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        //when(userService.getAge()).thenReturn(15);
        movieService = new MovieService(userService);
    }

    @org.junit.jupiter.api.Test
    void getMovieRecommendation_Adults() throws Exception {
        when(userService.getAge()).thenReturn(18);
        assertEquals("The Godfather, Deadpool, Saving Private Ryan", movieService.execute());
    }

    @org.junit.jupiter.api.Test
    void getMovieRecommendation_Teens() throws Exception {
        when(userService.getAge()).thenReturn(15);
        assertEquals("The Avengers, The Dark Knight, Inception", movieService.execute());
    }

    @org.junit.jupiter.api.Test
    void getMovieRecommendation_Children() throws Exception {
        when(userService.getAge()).thenReturn(10);
        assertEquals("Shrek, Coco, The Incredibles", movieService.execute());
    }

    @org.junit.jupiter.api.Test
    void getMovieRecommendation_userServiceException() throws Exception {
        when(userService.getAge()).thenThrow(Exception.class);
        assertEquals("Shrek, Coco, The Incredibles", movieService.execute());
    }

    @org.junit.jupiter.api.Test
    void getMovieRecommendation_userServiceSlow() throws Exception {
        when(userService.getAge()).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock i) throws InterruptedException {
                Thread.sleep(5000);
                return 50;
            }
        });
        assertEquals("Shrek, Coco, The Incredibles", movieService.execute());
    }


}