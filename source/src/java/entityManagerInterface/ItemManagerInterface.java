/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entityManagerInterface;

import entity.reservation.Item;

public interface ItemManagerInterface {
    
    public void persistItem(Item item);
    public Item findItem(Object id);
    public void removeItem(Item item);
    
}
