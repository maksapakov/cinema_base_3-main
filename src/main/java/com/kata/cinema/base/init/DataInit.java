package com.kata.cinema.base.init;

import com.kata.cinema.base.models.dto.request.AvailableOnlineMovieRequestDto;
import com.kata.cinema.base.models.entity.Collection;
import com.kata.cinema.base.models.entity.*;
import com.kata.cinema.base.models.enums.*;
import com.kata.cinema.base.repositories.NewsRepository;
import com.kata.cinema.base.repositories.ReviewRepository;
import com.kata.cinema.base.repositories.UserRepository;
import com.kata.cinema.base.service.entity.*;
import com.kata.cinema.base.service.entity.impl.CollectionServiceImp;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Component
@ConditionalOnExpression("${app.initializer.runInitialize}")
public class DataInit {
    private final MovieService movieService;
    private final GenreService genreService;
    private final CollectionService collectionService;
    private final RoleService roleService;
    private final UserService userService;
    private final FolderMovieService folderMovieService;
    private final PurchasedMovieService purchasedMovieService;
    private final AvailableOnlineService availableOnlineService;

    private final NewsService newsService;

    private final ReviewService reviewService;

    private final PasswordEncoder passwordEncoder;
    private final NewsRepository newsRepository;
    private RedactorCommentService redactorCommentService;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public DataInit(MovieService movieService, GenreService genreService, CollectionServiceImp collectionService,
                    RoleService roleService, UserService userService, FolderMovieService folderMovieService,
                    PurchasedMovieService purchasedMovieService, AvailableOnlineService availableOnlineService,
                    NewsService newsService, ReviewService reviewService, PasswordEncoder passwordEncoder,
                    NewsRepository newsRepository, RedactorCommentService redactorCommentService,
                    ReviewRepository reviewRepository,
                    UserRepository userRepository) {
        this.movieService = movieService;
        this.genreService = genreService;
        this.collectionService = collectionService;
        this.roleService = roleService;
        this.userService = userService;
        this.folderMovieService = folderMovieService;
        this.purchasedMovieService = purchasedMovieService;
        this.availableOnlineService = availableOnlineService;
        this.newsService = newsService; //добавление новостей в базу для теста
        this.reviewService = reviewService; //добавление ревью в базу
        this.passwordEncoder = passwordEncoder;
        this.newsRepository = newsRepository;
        this.redactorCommentService = redactorCommentService;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        createGenre();
        createMovie();
        createCollection();
        createRole();
        createUser();
        createFolderMovie();
        createNews();
        createReviews();
        createRedactorComment();
    }

    private void createNews() {
        for (int i = 1; i <= 30; i++) {
            News news = new News();
            news.setDate(
                    LocalDateTime.of(
                            LocalDate.ofEpochDay(ThreadLocalRandom.current()
                                    .nextLong(LocalDate.of(2022, Month.JANUARY, 1).toEpochDay(),
                                            LocalDate.now().toEpochDay())),
                            LocalTime.ofSecondOfDay(ThreadLocalRandom.current()
                                    .nextInt(0,86399))
                    )
            );

            news.setIsModerate(ThreadLocalRandom.current().nextBoolean());

            newsService.save(news);
        }
    }

    public void createGenre() {
        for (int i = 1; i <= 10; i++) {
            genreService.save(new Genre("Жанр" + i));
        }
    }

    //  for commit

    public void createMovie() {
        for (int i = 1; i <= 100; i++) {
            Movie movie = new Movie();
            movie.setName("фильм" + i);
            movie.setDataRelease(LocalDate.ofEpochDay(ThreadLocalRandom.current()
                    .nextLong(LocalDate.of(1990, Month.JANUARY, 1).toEpochDay(),
                            LocalDate.now().toEpochDay())));
            movie.setDescription("описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма\n" +
                    "описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма\n" +
                    "описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма\n" +
                    "описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма");

            List<MPAA> mpaaList = Arrays.asList(MPAA.values());
            movie.setMpaa(mpaaList.get(new SecureRandom().nextInt(mpaaList.size())));

            List<RARS> rarsList = Arrays.asList(RARS.values());
            movie.setRars(rarsList.get(new SecureRandom().nextInt(rarsList.size())));

            List<Genre> genreList = new ArrayList<>(genreService.getAll());
            int randomSize = ThreadLocalRandom.current().nextInt(1, 4);
            Collections.shuffle(genreList);
            movie.setGenres(new HashSet<>(genreList.subList(genreList.size() - randomSize, genreList.size())));

            movieService.save(movie);

            if (i % 2 == 0) {
                AvailableOnlineMovieRequestDto availableOnlineDto = new AvailableOnlineMovieRequestDto();
                availableOnlineDto.setAvailablePlus(true);
                availableOnlineDto.setBuyPrice(i * 100);
                availableOnlineDto.setRentalPrice(i * 20);
                Movie movieForOnline = movieService.getMovieByName(movie.getName());
                availableOnlineService.save(availableOnlineDto, movieForOnline.getId());
            }
        }
    }
    public void createCollection() {
        for (int i = 1; i <= 20; i++) {
            boolean enable = !Arrays.asList(2, 6, 10, 14, 18).contains(i);
            Collection collection = new Collection("Коллекция" + i, enable);

            List<Movie> movieList = new ArrayList<>(movieService.getAll());
            int randomSize = ThreadLocalRandom.current().nextInt(5, 16);
            Collections.shuffle(movieList);
            collection.setMovies(new HashSet<>(movieList.subList(movieList.size() - randomSize, movieList.size())));

            collectionService.save(collection);
        }
    }

    public void createRole() {
        roleService.save(new Role("USER"));
        roleService.save(new Role("ADMIN"));
        roleService.save(new Role("PUBLICIST"));
        roleService.save(new Role("REDACTOR"));
    }

    public void createUser() {
        for (int i = 1; i <= 27; i++) {
            User user = new User();

            user.setEmail("email" + i + "@mail.ru");
            user.setFirstName("Имя" + i);
            user.setLastName("Фамилия" + i);
            user.setPassword(passwordEncoder.encode("password"));

            LocalDate localDate = LocalDate.ofEpochDay(
                    ThreadLocalRandom.current().nextLong(
                            LocalDate.of(1970, 1, 1).toEpochDay(),
                            LocalDate.of(2010, 12, 31).toEpochDay()
                    )
            );

            user.setBirthday(localDate);
            user.setAvatarUrl("/uploads/users/avatar/#" + i);


            Set<Role> roles = new HashSet<>(Collections.singleton(roleService.getByName("USER")));
            switch (i) {
                default -> user.setRoles(roles);
                case 25 -> {
                    roles.add(roleService.getByName("REDACTOR"));
                    user.setRoles(roles);
                }
                case 26 -> {
                    roles.add(roleService.getByName("ADMIN"));
                    user.setRoles(roles);
                }
                case 27 -> {
                    roles.add(roleService.getByName("PUBLICIST"));
                    user.setRoles(roles);
                }
            }

            userService.save(user);

            for (int j = 1; j < 6; j++) {
                PurchasedMovie purchasedMovie = new PurchasedMovie();
                Random random = new Random();
                List<AvailableOnlineMovie> availableOnlineMovieList = availableOnlineService.getAll();
                int randomIndex = random.nextInt(availableOnlineMovieList.size());
                AvailableOnlineMovie movieForPurchase = availableOnlineMovieList.get(randomIndex);

                if (j % 2 == 0) {
                    purchasedMovie.setPurchase(PurchaseType.BUY);
                } else {
                    purchasedMovie.setPurchase(PurchaseType.RENT);
                }
                purchasedMovie.setEndDate(LocalDate.ofEpochDay(ThreadLocalRandom.current()
                        .nextLong(LocalDate.of(2022, Month.DECEMBER, 1).toEpochDay(),
                                LocalDate.of(2025, 12, 1).toEpochDay())));
                purchasedMovie.setUser(userService.getUserByEmail(user.getEmail()));
                purchasedMovie.setMovie(movieForPurchase);

                purchasedMovieService.save(purchasedMovie);
            }
        }
    }

    public void createFolderMovie() {
        List<Movie> movieList = new ArrayList<>(movieService.getAll());
        for (int i = 1; i <= 27; i++) {
            for (int k = 0; k < 3; k++) {
                FolderMovie folderMovie = new FolderMovie();
                folderMovie.setUser(userService.getById((long) i));

                switch (k) {
                    case 0 -> {
                        folderMovie.setCategory(Category.WAITING_MOVIES);
                        folderMovie.setName(Category.WAITING_MOVIES.getTranslation());
                    }
                    case 1 -> {
                        folderMovie.setCategory(Category.FAVORITE_MOVIES);
                        folderMovie.setName(Category.FAVORITE_MOVIES.getTranslation());
                    }
                    case 2 -> {
                        folderMovie.setCategory(Category.VIEWED_MOVIES);
                        folderMovie.setName(Category.VIEWED_MOVIES.getTranslation());
                    }
                }

                folderMovie.setPrivacy(Privacy.PUBLIC);
                folderMovie.setDescription("описание описание описание описание описание описание описание описание ");

                Set<Movie> movies = new HashSet<>();
                int amount = new Random().nextInt(5, 26);
                for (int j = 0; j < amount; j++) {
                    movies.add(movieList.get(new Random().nextInt(0, movieList.size())));
                }
                folderMovie.setMovies(movies);

                folderMovieService.save(folderMovie);
            }
        }
    }

    private void createReviews() {
        for (int i = 1; i <= 100; i++) {
            Review review = new Review();

            List<TypeReview> typeReviews = Arrays.asList(TypeReview.values());
            review.setTypeReview(typeReviews.get(new SecureRandom().nextInt(typeReviews.size())));

            review.setTitle("Заголовок ревью" + i);

            review.setDescription("Ревью" + i);

            review.setDate(LocalDate.ofEpochDay(ThreadLocalRandom.current()
                    .nextLong(LocalDate.of(2022, Month.JANUARY, 1).toEpochDay(),
                            LocalDate.now().toEpochDay())));

            List<User> userList = new ArrayList<>(userService.findAll());
            int randomUser = ThreadLocalRandom.current().nextInt(1,userList.size());
            review.setUser(userList.get(randomUser));

            List<Movie> movieList = new ArrayList<>(movieService.getAll());
            int randomMovie = ThreadLocalRandom.current().nextInt(1, movieList.size());
            review.setMovie(movieList.get(randomMovie));

            reviewService.save(review);
        }
    }

    private void createRedactorComment() {

        List<News> newsList = new ArrayList<>(newsService.getAll());
        Collections.shuffle(newsList);

        List<Review> reviewList = new ArrayList<>(reviewService.findAllWithFetch());
        Collections.shuffle(reviewList);

        for (int i = 1; i <= 20; i++) {

            Review review = reviewList.get(i);
            News news = newsList.get(i);

            RedactorComment redactorComment = new RedactorComment();

            redactorComment.setReview(review);

            redactorComment.setNews(news);

            redactorComment.setComment("Комменетарий" + i);

//            redactorComment.setUser(userService.getById(Long.valueOf(25)));
            redactorComment.setUser(userRepository.getById(25L));

            redactorCommentService.save(redactorComment);
        }
    }

}
