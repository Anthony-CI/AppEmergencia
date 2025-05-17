package pe.edu.upn.appemergencia;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import pe.edu.upn.appemergencia.Models.Emergencia;
import pe.edu.upn.appemergencia.Models.EmergenciaBD;

public class AdaptadorEmergencia extends RecyclerView.Adapter<AdaptadorEmergencia.EmergenciaViewHolder> {

    public AdaptadorEmergencia(Context contexto, List<EmergenciaBD> listaEmergencia) {
        this.contexto = contexto;
        this.listaEmergencia = listaEmergencia;
    }

    private Context contexto;
    private List<EmergenciaBD> listaEmergencia;
    public  AdaptadorEmergencia(){

    }
    @NonNull
    @Override
    public AdaptadorEmergencia.EmergenciaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(contexto).inflate(R.layout.ly_tarjeta_emergencia,parent,false);
        return new EmergenciaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorEmergencia.EmergenciaViewHolder holder, int position) {
        EmergenciaBD oE= listaEmergencia.get(position);
        holder.lbDescripcion.setText(oE.getDescripcion());
        holder.lbTipoEmergencia.setText(oE.getTipoEmergencia());
        holder.lbGravedad.setText(oE.getGravedad());
        holder.lbUrgencia.setText(oE.isUrgente()?"Es urgente":"No es urgente");
        //cargar foto de sql lite
        byte[] imagenBytes = oE.getFoto();
        if(imagenBytes != null && imagenBytes.length>0){
            Bitmap oBitmap = BitmapFactory.decodeByteArray(imagenBytes,0,imagenBytes.length);
            holder.imgItenFoto.setImageBitmap(oBitmap);
        }else {
            holder.imgItenFoto.setImageResource(R.mipmap.ic_launcher);
        }
    }

    @Override
    public int getItemCount() {
        return listaEmergencia.size();
    }


    //Traer todos los objetos de la tarjeta
    static class EmergenciaViewHolder extends RecyclerView.ViewHolder{
        TextView lbDescripcion,lbTipoEmergencia,lbGravedad,lbUrgencia;
        CircleImageView imgItenFoto;

        public EmergenciaViewHolder(@NonNull View itemView) {
            super(itemView);
            lbDescripcion = itemView.findViewById(R.id.lbTarjetaDescripcion);
            lbTipoEmergencia= itemView.findViewById(R.id.lbTarjetaTipoEmergencia);
            lbGravedad= itemView.findViewById(R.id.lbTarjetaGravedad);
            lbUrgencia= itemView.findViewById(R.id.lbTarjetaUrgencia);
            imgItenFoto= itemView.findViewById(R.id.imgTarjetaFoto);
        }
    }
}
