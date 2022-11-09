package com.library.benjaMainLib.controller.transformer;

import com.library.benjaMainLib.controller.model.PersonDetailView;
import com.library.benjaMainLib.controller.model.PersonSummaryView;
import com.library.benjaMainLib.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonTransformer {
    public PersonSummaryView transformSummaryView(Person person) {
        PersonSummaryView personSummaryView = new PersonSummaryView();
        personSummaryView.setId(person.getId());
        personSummaryView.setName(person.getName());
        return personSummaryView;
    }

    public PersonDetailView transformDetailView(Person person) {
        PersonDetailView personDetailView = new PersonDetailView();
        personDetailView.setId(person.getId());
        personDetailView.setName(person.getName());
        personDetailView.setRole(person.getRole());
        return personDetailView;
    }
}
