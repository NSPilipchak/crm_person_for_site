package dal;

import java.util.ArrayList;

import blogic.BaseStatus;
import blogic.BookingPerson;
import blogic.Smena;
import sun.net.www.content.text.Generic;

public interface PersonDAO
{
	void create(BookingPerson p);
	ArrayList<BookingPerson> read();
	void update(BookingPerson p);
	void delete(BookingPerson p);
	String[][] checkBase(int smenaSize);
	int[] checkReserv(int smenaSize);
	public ArrayList<Smena> readSmena();
	void updateSmena(Smena s);
	String[] readSmenaString();
	ArrayList<BookingPerson> searchPerson(String search, int parametr, boolean strongSearch);
}
