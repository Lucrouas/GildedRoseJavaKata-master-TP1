package com.pbz4esilv.gildedrose;


import java.util.ArrayList;
import java.util.List;

public class Inventory implements Quality{

    private static List<Item> items = null;



    public static void Inventory() {
        super();
        items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));

    }


    public static void AjouterItem(Item item) //Ajout d'un item
    {
        if (items == null)
            items = new ArrayList<Item>();
        items.add(item);
    }

      public static void RetirerItem(Item item) //Suppression d'un item
        {
            if (items ==null)
                return;
            else items.remove(item);
        }

    public void AfficherInventaire() // Affichage de l'inventaire
    {
        for (int i = 0; i < items.size(); i++)
        {
            System.out.println(items.get(i).getName());
            System.out.println(items.get(i).getSellIn());
            System.out.println(items.get(i).getQuality());

        }

    }

    public static int TailleInventaire() {
        return items.size();
    }

    public static Item AccederAUnitem(int index) {
        return items.get(index);
    }


    public static void MAJQualite()
    {
        for (Item item : items)
        {
            MAJQualite(item);
        }
    }

    private static void MAJQualite(Item item)
    {
        if (EstUnSulfuras(item))
            return;

        MAJQualiteAvantDateVente(item);
        DateMoinsUn(item);
        MAJQualiteApresDateVente(item);
    }

    private static void MAJQualiteAvantDateVente(Item item)
    {
        if (EstUnProduitSpecial(item))
        {
            MiseAJourQualiteSpeciaux(item);
        }
        else
        {
            MAJQualiteNonspeciaux(item);
        }
    }

    private static void MAJQualiteApresDateVente(Item item)
    {
        if (!DateDeVentePassee(item))
            return;

        if (EstUnAgedBrie(item))
        {
            QualitePlusUn(item);
        }
        else
        {
            BaisserQualite(item);
        }
    }

    private static boolean EstUnConjured(Item item)
    {
        return item.getName().contains("Conjured");
    }

    private static boolean EstUnProduitSpecial(Item item)
    {
        return EstUnAgedBrie(item) || EstUnBackstagePasses(item);
    }

    private static boolean EstUnAgedBrie(Item item)
    {
        return "Aged Brie".equals(item.getName());
    }

    private static boolean EstUnBackstagePasses(Item item) {
        return "Backstage passes to a TAFKAL80ETC concert".equals(item
                .getName());
    }

    private static boolean EstUnSulfuras(Item item)
    {
        return "Sulfuras, Hand of Ragnaros".equals(item.getName());
    }

    private static void MiseAJourQualiteSpeciaux(Item item) {
        QualitePlusUn(item);

        if (EstUnBackstagePasses(item)) {
            MAJQualiteBackstagePasses(item);
        }
    }

    private static void MAJQualiteNonspeciaux(Item item)
    {
        QualiteMoinsUn(item);

        if (EstUnConjured(item))
        {
            QualiteMoinsUn(item);
        }
    }

    private staticvoid BaisserQualite(Item item) {
        if (EstUnBackstagePasses(item)) {
            QualiteAZero(item);
        } else {
            MAJQualiteNonspeciaux(item);
        }
    }

    private static void QualiteAZero(Item item) {
        item.setQuality(0);
    }

    private static void DateMoinsUn(Item item) {
        item.setSellIn(item.getSellIn() - 1);
    }

    private static boolean DateDeVentePassee(Item item) {
        return item.getSellIn() < 0;
    }

    private static void MAJQualiteBackstagePasses(Item item) {
        if (item.getSellIn() < 11) {
            QualitePlusUn(item);
        }

        if (item.getSellIn() < 6) {
            QualitePlusUn(item);
        }
    }

    private static void QualitePlusUn(Item item) {
        if (QualiteNonMaximale(item)) {
            item.setQuality(item.getQuality() + 1);
        }
    }
    private static boolean QualiteNonMaximale(Item item) {
        return item.getQuality() < 50;
    }

    private static void QualiteMoinsUn(Item item) {
        if (QualiteNonNulle(item)) {
            item.setQuality(item.getQuality() - 1);
        }
    }

    private static boolean QualiteNonNulle(Item item) {
        return item.getQuality() > 0;
    }
}


