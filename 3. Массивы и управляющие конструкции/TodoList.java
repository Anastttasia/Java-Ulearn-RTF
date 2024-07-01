public class TodoList {
	
	ArrayList<String> todos;

    public static void main(String[] args) {
        // TODO: написать консольное приложение для работы со списком дел todoList
    }
	
	public TodoList() {
		this.todos = new ArrayList<String>();
	}

    public void add(String todo) {
		// TODO: добавьте переданное дело в конец списка
		if (this.todos == null) return;

		this.todos.add(todo);
    }

    public void add(int index, String todo) {
        // TODO: добавьте дело на указаный индекс,
		//  проверьте возможность добавления
		if (this.todos == null) return;

		if (index < 0 || index <= this.todos.size()) {
			this.todos.add(index, todo);
		}
    }

    public void edit(String todo, int index) {
        // TODO: заменить дело на index переданным todo индекс,
		//  проверьте возможность изменения
		if (this.todos == null) return;

		if (index < 0 || index <= this.todos.size()) {
			this.todos.set(index, todo);
		}
    }

    public void delete(int index) {
//         TODO: удалить дело находящееся по переданному индексу,
		//          проверьте возможность удаления дела
		if (this.todos == null) return;

		if (index < 0 || index <= this.todos.size()) {
			this.todos.remove(index);
		}
    }

    public ArrayList<String> getTodos() {
        // TODO: вернуть список дел
        return this.todos;
    }
}
