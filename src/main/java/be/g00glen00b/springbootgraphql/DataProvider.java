package be.g00glen00b.springbootgraphql;

import be.g00glen00b.springbootgraphql.alumne.Alumne;
import be.g00glen00b.springbootgraphql.alumne.AlumneRepository;
import be.g00glen00b.springbootgraphql.article.Article;
import be.g00glen00b.springbootgraphql.article.ArticleRepository;
import be.g00glen00b.springbootgraphql.comment.Comment;
import be.g00glen00b.springbootgraphql.comment.CommentRepository;
import be.g00glen00b.springbootgraphql.partida.Partida;
import be.g00glen00b.springbootgraphql.partida.PartidaRepository;
import be.g00glen00b.springbootgraphql.profile.Profile;
import be.g00glen00b.springbootgraphql.profile.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static be.g00glen00b.springbootgraphql.alumne.Alumne.House.GRYFFINDOR;
import static be.g00glen00b.springbootgraphql.alumne.Alumne.House.SLYTHERIN;

@Component
@AllArgsConstructor
public class DataProvider implements CommandLineRunner {
    private CommentRepository commentRepository;
    private ArticleRepository articleRepository;
    private ProfileRepository profileRepository;
    private AlumneRepository alumneRepository;
    private PartidaRepository partidaRepository;


    @Override
    @Transactional
    public void run(String... strings) {
        Profile author = profileRepository.save(new Profile(null, "g00glen00b", "The author of this blog"));
        Profile admin = profileRepository.save(new Profile(null, "admin", "The administrator of this blog"));
        Article article1 = articleRepository.save(new Article(null, "Hello world", "This is a hello world", author.getId()));
        Article article2 = articleRepository.save(new Article(null, "Foo", "Bar", admin.getId()));
        commentRepository.save(new Comment(null, "Do you like this article?", article1.getId(), author.getId()));
        commentRepository.save(new Comment(null, "This is a great article", article1.getId(), admin.getId()));
        commentRepository.save(new Comment(null, "This is a comment", article2.getId(), admin.getId()));
        Alumne alumne1 = alumneRepository.save(new Alumne(null, "Harry", GRYFFINDOR));
        Alumne alumne2 = alumneRepository.save(new Alumne(null, "Ron", SLYTHERIN));
        partidaRepository.save(new Partida(null, 13, true));
        partidaRepository.save(new Partida(null, 33, false));
        partidaRepository.save(new Partida(null, 14, true));
        partidaRepository.save(new Partida(null, 23, true));
    }
}
