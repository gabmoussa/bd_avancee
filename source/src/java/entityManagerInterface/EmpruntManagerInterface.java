/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityManagerInterface;

import entity.reservation.Emprunt;


public interface EmpruntManagerInterface {
    public void persistEmprunt(Emprunt emprunt);
    public Emprunt findEmprunt(Object id);
    public void removeEmprunt(Emprunt emprunt);
}
