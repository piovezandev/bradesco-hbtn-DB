import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class UsuarioOperations {

	private final MongoCollection<Document> collection;

	public UsuarioOperations(MongoDatabase database) {
		this.collection = database.getCollection("usuarios");
	}

	public static void main(String[] args) {
		MongoDBConnection connection = new MongoDBConnection();

		if (connection.getDatabase() != null) {
			System.out.println("Banco de dados: " + connection.getDatabase().getName());
		}
		UsuarioOperations usuarioOperations = new UsuarioOperations(connection.getDatabase());

		usuarioOperations.createUser(new Usuario("Alice", 25));
		usuarioOperations.createUser(new Usuario("Bob", 30));
		usuarioOperations.createUser(new Usuario("Charlie", 28));

		usuarioOperations.findUsers();

		usuarioOperations.updateOne();

		usuarioOperations.findUsers();

		usuarioOperations.deleteOne();

		usuarioOperations.findUsers();

		connection.closeConnection();
	}

	public void createUser(Usuario usuario) {
		collection.insertOne(usuario.toDocument());
		System.out.println("Usuario adicionado: " + usuario);
	}

	public void findUsers() {
		for (Document doc : collection.find()) {
			Usuario usuario = Usuario.fromDocument(doc);
			System.out.println(usuario);
		}
	}

	public void updateOne() {
		collection.updateOne(eq("nome", "Bob"), set("idade", 32));
		System.out.println("usuario alterado");
	}

	public void deleteOne() {
		collection.deleteOne(eq("nome", "Charlie"));
		System.out.println("usuario removido");
	}
}
