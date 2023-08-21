package saulo.io.projetoMongoDB.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import saulo.io.projetoMongoDB.entity.Useres;
import saulo.io.projetoMongoDB.repository.UseresRepository;

@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	UseresRepository repository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		List<Useres> lUseres = repository.findAll();

		if (lUseres.isEmpty()) {

			createdUseres("Saulo", "saulo.costa@");
			createdUseres("Gabriel", "gabriel.costa@");
			createdUseres("Dany", "dany.alvares@");
			createdUseres("Daniella", "daniella.alvares@");
		}

		// Busca Lista de dados
		List<Useres> consulta = repository.findAll();

		for (Useres useres : consulta) {

			System.out.println("1 -" + useres.getName());
		}

		// Deleta algum registro pelo nome
		// repository.deleteByName("Daniella");

		// Atualiza algum registro pelo ID
		// Useres update = repository.findByName("Saulo");
		// System.out.println("2 -" + update.getName());
		// update.setName("Saulo Costa");

		// Salva dados Atualizados
		// repository.save(update);

		// Busca um dado pelo meu método customizado
		Useres useresName = repository.findByName("Saulo Costa");
		System.out.println("2 -" + useresName.getName());

		// Busca um dado pelo metodo customizado usando @Query
		Useres useresLike = repository.findByNameLike("Ga");
		System.out.println("3 -" + useresLike.getName());

		// Busca um dado pelo nome ignorando o case
		Useres useres = repository.findByNameIgnoreCase("dany");
		System.out.println("4 -" + useres.getName());

		// Busca Lista de dados @Query
		List<Useres> consultaQuery = repository.findByFirstnameprimeiro("Gabriel");

		for (Useres usere : consultaQuery) {

			System.out.println("5 -" + usere.getName());
		}

	}

	public void createdUseres(String name, String email) {

		Useres useres = new Useres(name, email);

		repository.save(useres);
	}

}
