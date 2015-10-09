package com.pbz4esilv.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

/**

 * Created by Luc on 08/10/2015.
 */
public class InventoryTest {

    private void ItemsSontEgaux(Item item, Item autreItem) {
        assertTrue(item.name.equals(autreItem.name));
        assertTrue(item.sellIn == autreItem.sellIn);
        assertTrue(item.quality == autreItem.quality);
    }


    private void joursEcoules(int nbrDeJours) {
        for (int i = 0; i < nbrDeJours; i++) {
            Inventory.MAJQualite();
        }
    }

    private void assertItemsQuality(int quality, Item item) {
        assertEquals(quality, item.getQuality());
    }



    @Test
    public void laQualiteNePeutPasEtreSuperieureA50() {
        Item agedBrie = new Item("Aged Brie", 2, 0);
        Inventory.AjouterItem(agedBrie);

        joursEcoules(300);

        assertItemsQuality(50, agedBrie);
    }

    @Test
    public void ajouterItemALaListe() {
        Item agedBrie = new Item("Aged Brie", 2, 0);
        Inventory.AjouterItem(agedBrie);

        assertEquals(1, Inventory.TailleInventaire());
        ItemsSontEgaux(agedBrie, Inventory.AccederAUnitem(0));
    }

    @Test
    public void laQualiteNePeutPasEtreInferieureAzero() {
        Item item = new Item("+5 Dexterity Vest", 10, 20);
        Inventory.AjouterItem(item);

        joursEcoules(200);

        assertItemsQuality(0, item);
    }

}