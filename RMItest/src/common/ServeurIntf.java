package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import client.Client;

public interface ServeurIntf extends Remote{
	public String messageBienvenue() throws RemoteException;
	
	void envoieMsg(String message) throws RemoteException;
	List<String> renvoieList() throws RemoteException;}
