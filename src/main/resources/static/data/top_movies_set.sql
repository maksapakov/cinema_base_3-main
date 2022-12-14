create or replace procedure top_movie_set(
    in in_score_amount int,
    in in_limit int
)
as
$$
begin

    delete
    from top_movies
    where movie_id > 0;

    create table if not exists uniq_movie_id
    (
        id       bigint generated by default as identity primary key,
        movie_id bigint,
        average  double precision
    );

    insert into uniq_movie_id (movie_id)
    select distinct movie_id
    from score;

    declare
        mi  bigint;
        avg int;
    begin
        for count in 1..(select count(*) from uniq_movie_id)
            loop
                mi = movie_id from uniq_movie_id umi where umi.id = count;
                avg = avg(score) from score where movie_id = mi;
                if (select count(movie_id)
                    from score
                    where movie_id = mi) >= in_score_amount
                then
                    update uniq_movie_id set average = avg where id = count;
                else
                    delete from uniq_movie_id where id = count;
                end if;
            end loop;
    end;

    insert into top_movies (rating, movie_id)
    select round(average), movie_id
    from uniq_movie_id
    order by average desc
    limit in_limit;

    drop table uniq_movie_id;
end;
$$
    language plpgsql