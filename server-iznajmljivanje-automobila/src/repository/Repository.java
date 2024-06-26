/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;

/**
 *
 * @author Filip
 * @param <T>
 */
public interface Repository<T> {
    
    public void dodaj(T param) throws Exception;

    public void promeni(T param) throws Exception;

    public void obrisi(T param) throws Exception;

    public List<T> vratiSve(T param) throws Exception;

    public T uloguj(T param) throws Exception;
    
    public List<T> filter(T param)throws Exception;
    
    public T nadji(T param) throws Exception;
}
