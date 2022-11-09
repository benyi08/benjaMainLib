package com.library.benjaMainLib.transformerTests;

import com.library.benjaMainLib.controller.model.PersonDetailView;
import com.library.benjaMainLib.controller.model.PersonSummaryView;
import com.library.benjaMainLib.controller.transformer.PersonTransformer;
import com.library.benjaMainLib.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PersonTransformerTest {

    private final PersonTransformer underTest = new PersonTransformer();

    @Test
    public void testTransformSummaryViewShouldWork() {
        // given
        Person person = new Person(1, "Benike", "pwd", "role1");
        // when
        PersonSummaryView personSummaryView = underTest.transformSummaryView(person);
        // then
        Assertions.assertEquals(person.getId(), personSummaryView.getId());
        Assertions.assertEquals(person.getName(), personSummaryView.getName());
    }

    @Test
    public void testTransformDetailViewShouldWork() {
        // given
        Person person = new Person(1, "Benike", "pwd", "role1");
        // when
        PersonDetailView personDetailView = underTest.transformDetailView(person);
        // then
        Assertions.assertEquals(person.getId(), personDetailView.getId());
        Assertions.assertEquals(person.getName(), personDetailView.getName());
    }
}
