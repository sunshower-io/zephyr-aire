package io.zephyr.aire.elements;

import com.vaadin.flow.component.BlurNotifier;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.shared.Registration;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractToolbarElement extends HorizontalLayout {
    protected final List<Registration> registrations;

    public AbstractToolbarElement() {
        this.registrations = new ArrayList<>();
    }

    @Override
    public void add(Component... components) {
      super.add(components);
      for (Component c : components) {
        if (c instanceof Button) {
          this.addListeners((Button) c);
        }
      }
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        super.onDetach(detachEvent);
        for (Registration r : this.registrations) {
            r.remove();
        }
    }

     void addListeners(Button button) {
      this.registrations.add(button.addClickListener(this::onClick));
      this.registrations.add(button.addBlurListener(this::onBlur));
    }

     void onClick(ClickEvent<Button> event) {
      event.getSource().addClassName("active");
    }

     void onBlur(BlurNotifier.BlurEvent<Button> event) {
      event.getSource().removeClassName("active");
    }
}
