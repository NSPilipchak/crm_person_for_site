package dal;

import blogic.BookingPerson;
import blogic.BookingPerson2017;
import blogic.Smena;

import java.util.ArrayList;

public interface PersonDAO2017
{
	void create(BookingPerson2017 p);
	ArrayList<BookingPerson2017> read();
	void update(BookingPerson2017 p);
	void delete(BookingPerson2017 p);
	String[] checkBase();
	public ArrayList<Smena> readSmena();
	void updateSmena(Smena s);
	ArrayList<BookingPerson2017> getPersonByChekinn(String inn);
}
