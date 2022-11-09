package com.library.benjaMainLib.serviceTests;

import com.library.benjaMainLib.model.Person;
import com.library.benjaMainLib.repository.PersonRepo;
import com.library.benjaMainLib.service.PersonService;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PersonServiceTest {

    @Mock
    private PersonRepo personRepo;

    @InjectMocks
    private PersonService underTest;

    @Test
    public void testGetAllPerson(){
        //given
        Person person1 = new Person(1, "Olika", "anya", "customer");
        Person person2 = new Person(2, "Mirjámka", "olika", "customer");
        Person person3 = new Person(3, "Arnika", "olika", "librarian");
        List<Person> peopleList = List.of(person1, person2, person3);
        when(personRepo.findAll()).thenReturn(List.of(person1, person2, person3));
        //when
        List<Person> testPeopleList = underTest.getAllPerson();
        //then
        assertEquals(3, peopleList.size());
        Person personNumber2 = testPeopleList.get(1);
        assertEquals(personNumber2.getId(), person2.getId());
        assertEquals(personNumber2.getName(), person2.getName());
        assertEquals(personNumber2.getPassword(), person2.getPassword());
        assertEquals(personNumber2.getRole(), person2.getRole());
        verify(personRepo, times(1)).findAll();
    }

    @Test
    public void testGetOnePersonShouldGiveBackOnePerson(){
        //given
        Person person = new Person(1, "Olika", "anya", "customer");
        when(personRepo.findById(1)).thenReturn(Optional.of(person));
        when(personRepo.findById(2)).thenReturn(Optional.empty());
        //when
        Optional<Person> testPerson = underTest.getOnePerson(1);
        Optional<Person> testPerson2 = underTest.getOnePerson(2);
        //then
        assertEquals(person, testPerson.get());
        assertEquals(Optional.empty(), testPerson2);
        assertEquals(person.getId(), testPerson.get().getId());
        assertEquals(person.getName(), testPerson.get().getName());
        assertEquals(person.getPassword(), testPerson.get().getPassword());
        assertEquals(person.getRole(), testPerson.get().getRole());
        verify(personRepo, times(1)).findById(1);
        verify(personRepo, times(1)).findById(2);
    }

    @Test
    public void testCreateOnePersonCreatesPesron(){
        //given
        Person person = new Person(1, "Olika", "anya", "customer");
        //when
        underTest.createOnePerson(person);
        //then
        verify(personRepo).save(person);
    }

    @Test
    public void testDeletePersonDeletesPerson(){
        //given
        Person person = new Person(1, "Olika", "anya", "customer");
        when(personRepo.findById(1)).thenReturn(Optional.of(person));
        //when
        underTest.delete(1);
        //then
        verify(personRepo).deleteById(1);
    }

}
