package projet.poa.uqac.colocmanager.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import projet.poa.uqac.colocmanager.Facture;
import projet.poa.uqac.colocmanager.R;

/**
 * Created by Arnaud
 */
public class FactureAdapter extends BaseAdapter {

    ArrayList<Facture> listBills;

    private Context mContext;

    private TextView tv_titre;

    private TextView tv_Date;

    private TextView tv_Magasin;

    private TextView tv_description;

    private TextView tv_user;

    private TextView tv_valeur;

    private CheckBox cb_isReglee;

    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;

    public FactureAdapter(Context context)
    {
        mContext=context;
        listBills = new ArrayList<>();
        mInflater=LayoutInflater.from(mContext);
    }

    public int getCount()
    {
        return listBills.size();
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {

        LinearLayout layoutItem;
        // Récupération du layout lié à notre GA
        if(convertView==null)
        {
            // Initialisation du layoutItem à partir du layout XML GA_layout.xml
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.facture_adapter, parent, false);
        }
        else
            layoutItem = (LinearLayout) convertView;

        tv_titre = (TextView) layoutItem.findViewById(R.id.TV_titreFacture);
        tv_Date = (TextView) layoutItem.findViewById(R.id.TV_date_facture);
        tv_description = (TextView) layoutItem.findViewById(R.id.TV_DescriptionFacture);
        tv_Magasin = (TextView) layoutItem.findViewById(R.id.TV_Magasin_Achat);
        tv_user = (TextView) layoutItem.findViewById(R.id.TV_PersonneIntervenant);
        tv_valeur = (TextView) layoutItem.findViewById(R.id.TV_PrixAchat);
        cb_isReglee = (CheckBox) layoutItem.findViewById(R.id.CB_isReglee);

        tv_titre.setText(listBills.get(position).getTitre());

        String date = "Date : " + listBills.get(position).getDateAchat();
        tv_Date.setText(date);

        String magasin = "Magasin : " + listBills.get(position).getMagasinAchat();
        tv_Magasin.setText(magasin);

        tv_description.setText(listBills.get(position).getDescription());

        String utilisateur = "Réglée pour : " + listBills.get(position).getUtilisateurIntervenant().getPseudo();
        tv_user.setText(utilisateur);

        String valeur = Double.toString(listBills.get(position).getCoutParPersonne());
        tv_valeur.setText(valeur);

        if(!listBills.get(position).isFactureReglee())
        {
            cb_isReglee.setChecked(false);
            cb_isReglee.setEnabled(true);
        }
        else if (listBills.get(position).isFactureReglee())
        {
            cb_isReglee.setChecked(true);
            cb_isReglee.setEnabled(false);
        }



        return layoutItem;
    }

    public Facture getItem(int position) {return listBills.get(position);}

    public long getItemId(int position)
    {
        return position;
    }

    public void updateListView(ArrayList<Facture> listf)
    {
        listBills = listf;
    }

}
