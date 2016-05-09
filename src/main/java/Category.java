import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Category {
  private int id;
  private String name;

  public Category(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public static List<Category> all() {
    String sql = "SELECT id, name FROM Categories";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Category.class);
    }
  }

  @Override
  public boolean equals(Object otherCategory) {
    if (!(otherCategory instanceof Category)) {
      return false;
    } else {
      Category newCategory = (Category) otherCategory;
      return this.getName().equals(newCategory.getName()) &&
             this.getId() == newCategory.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO Categories(name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Category find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM Categories where id=:id";
      Category category = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Category.class);
      return category;
    }
  }

  public void addTask(Task task) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO tasks_categories (task_id, category_id) VALUES (:task_id, :category_id)";
      con.createQuery(sql)
        .addParameter("task_id", task.getId())
        .addParameter("category_id", this.getId())
        .executeUpdate();
    }
  }

  public List<Task> getTasks(){
    try(Connection con = DB.sql2o.open()){
      String joinQuery = "SELECT task_id FROM tasks_categories WHERE category_id = :category_id";
      List<Integer> taskIds = con.createQuery(joinQuery)
        .addParameter("category_id", this.getId())
        .executeAndFetch(Integer.class);

      List<Task> tasks = new ArrayList<Task>();

      for (Integer taskId : taskIds) {
        String taskQuery = "Select * From tasks WHERE id = :taskId";
        Task task = con.createQuery(taskQuery)
          .addParameter("taskId", taskId)
          .executeAndFetchFirst(Task.class);
        tasks.add(task);
      }
      return tasks;
    }

  }

}
