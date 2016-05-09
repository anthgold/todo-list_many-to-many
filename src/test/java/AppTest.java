import org.sql2o.*;
import org.junit.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.junit.Assert.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("To Do List!");
  }

  @Test
  public void categoryIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Categories"));
    fill("#name").with("Household chores");
    submit("#catBtn");
    assertThat(pageSource()).contains("Household chores");
  }

  // failed to include an early bit of code in App.Java, so probably built out more in the .vtls and App.Java in advance of the tests here

  // @Test
  // public void taskIsCreatedTest() {
  //   goTo("http://localhost:4567/");
  //   click("a", withText("Tasks"));
  //   fill("#description").with("Buy milk");
  //   submit("#catBtn");
  //   assertThat(pageSource()).contains("Buy milk");
  // }

  // this one seems gone
  // @Test
  // public void categoryIsDisplayedTest() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   String categoryPath = String.format("http://localhost:4567/categories/%d", myCategory.getId());
  //   goTo(categoryPath);
  //   assertThat(pageSource()).contains("Household chores");
  // }


  //  @Test
  //  public void categoryShowPageDiplayName() {
  //    goTo("http://localhost:4567/categories/new");
  //    fill("#name").with("Household cheese");
  //    submit(".btn");
  //    click("a", withText("View categories"));
  //    click("a", withText("Household cheese"));
  //    assertThat(pageSource()).contains("Household cheese");
  //  }
  //
  //  @Test
  //  public void categoryTasksFormIsDisplayed() {
  //    goTo("http://localhost:4567/categories/new");
  //    fill("#name").with("Shopping");
  //    submit(".btn");
  //    click("a", withText("View categories"));
  //    click("a", withText("Shopping"));
  //    click("a", withText("Add a new task"));
  //    assertThat(pageSource()).contains("Add a task to Shopping");
  //  }
  //
  //  @Test
  //  public void allTasksDisplayDescriptionOnCategoryPage() {
  //    Category myCategory = new Category ("Household chores");
  //    myCategory.save();
  //    Task firstTask = new Task ("Mow the lawn", myCategory.getId());
  //    firstTask.save();
  //    Task secondTask = new Task("Do the dishes", myCategory.getId());
  //    secondTask.save();
  //    String categoryPath = String.format("http://localhost:4567/categories/%d", myCategory.getId());
  //    goTo(categoryPath);
  //    assertThat(pageSource()).contains("Mow the lawn");
  //    assertThat(pageSource()).contains("Do the dishes");
  //  }
  //
  // @Test
  // public void taskShowPage() {
  //   Category myCategory = new Category("Home");
  //   myCategory.save();
  //   Task myTask = new Task("Clean", myCategory.getId());
  //   myTask.save();
  //   String categoryPath = String.format("http://localhost:4567/categories/%d", myCategory.getId());
  //   goTo(categoryPath);
  //   click("a", withText("Clean"));
  //   assertThat(pageSource()).contains("Clean");
  //   assertThat(pageSource()).contains("Return to Home");
  // }
  //
  // @Test
  // public void taskUpdate() {
  //   Category myCategory = new Category("Home");
  //   myCategory.save();
  //   Task myTask = new Task("Clean", myCategory.getId());
  //   myTask.save();
  //   String taskPath = String.format("http://localhost:4567/categories/%d/tasks/%d", myCategory.getId(), myTask.getId());
  //   goTo(taskPath);
  //   fill("#description").with("Dance");
  //   submit("#update-task");
  //   assertThat(pageSource()).contains("Dance");
  // }
  //
  // @Test
  // public void taskDelete() {
  //   Category myCategory = new Category("Home");
  //   myCategory.save();
  //   Task myTask = new Task("Clean", myCategory.getId());
  //   myTask.save();
  //   String taskPath = String.format("http://localhost:4567/categories/%d/tasks/%d", myCategory.getId(), myTask.getId());
  //   goTo(taskPath);
  //   submit("#delete-task");
  //   assertEquals(0, Task.all().size());
  // }

}
