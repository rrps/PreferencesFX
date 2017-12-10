package com.dlsc.preferencesfx.formsfx.view.renderer;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.view.util.ViewMixin;
import com.dlsc.preferencesfx.formsfx.view.controls.SimpleControl;
import com.dlsc.preferencesfx.formsfx.view.controls.SimpleNumberControl;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class PreferencesGroupRenderer {

  /**
   * SPACING is used to set the spacing of the group as well as the
   * spacing for vertical/horizontal gaps between controls.
   */
  private static final double SPACING = 10;
  /**
   * Add the controls in the GridPane in a 12-column layout. If a control
   * takes up too much horizontal space, wrap it to the next row.
   */
  private static final int COLUMN_COUNT = 12;
  private Label titleLabel;
  private GridPane grid;
  private PreferencesGroup preferencesGroup;

  /**
   * This is the constructor to pass over data.
   *
   * @param preferencesGroup The PreferencesGroup which gets rendered.
   */
  PreferencesGroupRenderer(PreferencesGroup preferencesGroup, GridPane grid) {
    this.preferencesGroup = preferencesGroup;
    this.grid = grid;
    preferencesGroup.setRenderer(this);
    init();
  }

  public void init() {
    //this.initializeSelf();
    this.initializeParts();
    this.layoutParts();
    //this.setupEventHandlers();
    //this.setupValueChangedListeners();
    this.setupBindings();
  }

  public void initializeParts() {
    titleLabel = new Label();
  }

  public void layoutParts() {

    // Only when the preferencesGroup has a title
    if (preferencesGroup.getTitle() != null) {
      grid.add(titleLabel, grid.getRowConstraints().size(), 0, 1, 1);
      grid.getStyleClass().add("category-content");
    }

    List<Field> fields = preferencesGroup.getFields();

    for (int i = 0; i < fields.size(); i++) {
      Field field = fields.get(i);
      SimpleControl c = (SimpleControl) field.getRenderer();
      c.setField(field);
      grid.add(c.getFieldLabel(), grid.getRowConstraints().size(), i, 1, 1);
      grid.add(c.getNode(), grid.getRowConstraints().size()+1, i, 1, 1);
    }

    // Styling
    grid.setHgap(SPACING);
    grid.setVgap(SPACING);
    //setPadding(new Insets(SPACING * 1.5));
    titleLabel.getStyleClass().add("category-title");
  }

  public void setupBindings() {
    titleLabel.textProperty().bind(preferencesGroup.titleProperty());
  }

  /**
   * Adds a style class to the group.
   *
   * @param name of the style class to be added to the group
   */
  public void addStyleClass(String name) {
    titleLabel.getStyleClass().add(name);
  }

  /**
   * Removes a style class from the group.
   *
   * @param name of the class to be removed from the group
   */
  public void removeStyleClass(String name) {
    titleLabel.getStyleClass().remove(name);
  }

  public Label getTitleLabel() {
    return titleLabel;
  }
}
