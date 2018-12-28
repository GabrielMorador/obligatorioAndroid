package com.example.bestobislasfiji.obligatorioandroid;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;


public class AdminSQLiteHelper extends SQLiteOpenHelper {

    private Context contexto;

    public AdminSQLiteHelper(Context contexto) {
        super(contexto,BaseDatos.BD_NAME,null,BaseDatos.BD_VERSION);

    this.contexto=contexto;
    }


    @Override
    public void onCreate(SQLiteDatabase bio) {
        bio.execSQL(BaseDatos.Prodctos.SQL_CREAR_TABLA_PRODUCTOS);
        bio.execSQL(BaseDatos.Pedidos.SQL_CREAR_TABLA_PEDIDOS);

        bio.execSQL(new StringBuilder("insert into ")
                .append(BaseDatos.PRODUCTOS)
                .append(" values(null,'Vehículos','Toyota AE86','La generación AE86 del Toyota Corolla Levin y Toyota Sprinter Trueno" +
                        " es un pequeño coupé ligero o hatchback introducido por Toyota en 1983 como parte de la quinta generación alineación Toyota Corolla.'" +
                        ",25555.50,null);")
                .toString());

        bio.execSQL(new StringBuilder("insert into ")
                .append(BaseDatos.PRODUCTOS)
                .append(" values(null,'Vehículos','Subaru Impreza','El Subaru WRX STI es un deportivo de cuatro puertas con 300 CV, cambio de marchas manual y tracción total." +
                        "Sobresale por la eficacia de su sistema de tracción total (con múltiples posibilidades de reglaje para variar su funcionamiento), agilidad en curva, " +
                        "capacidad de frenada y por el extraordinario ajuste de su suspensión.',55555.50,null);")
                .toString());

        bio.execSQL(new StringBuilder("insert into ")
                .append(BaseDatos.PRODUCTOS)
                .append(" values(null,'Vehículos','BMW M3','El primer M3 fue basado en el serie E30 que se comercializó en el año 1986, y desde entonces cada Serie 3" +
                        " tuvo su versión M. A finales del 2007 se lanzó el M3 E92, con una potencia estimada en 420 CV. Desde el E30 BMW ha ido aumentando caballos " +
                        "y cilindros desde los cuatro en línea hasta el E92 que es un V8. ',10589.00,null);")
                .toString());

        bio.execSQL(new StringBuilder("insert into ")
                .append(BaseDatos.PRODUCTOS)
                .append(" values(null,'Vehículos','Ford Focus','El Focus RS 2018, de edición limitada, llega con modificaciones inspiradas en la comunidad de entusiastas." +
                        " Exclusivo del modelo 2018: tapas de espejos en Black y alerón Black con nueva calcomanía RS, detalles interiores de fibra de carbono, ruedas forjadas estándar" +
                        " con cubiertas centrales RS, y los asientos térmicos tapizados en cuero RECARO® ahora estándar, espejos y volante térmicos y navegación activada por voz. ',100000.50,null);")
                .toString());

        bio.execSQL(new StringBuilder("insert into ")
                .append(BaseDatos.PRODUCTOS)
                .append(" values(null,'Libros','Canción de hielo y fuego','Canción de Hielo y Fuego es una multipremiada serie de novelas y novelas cortas de fantasía épica escritas por el " +
                        "novelista y guionista estadounidense George R. R. Martin. Martin comenzó a escribir la serie en 1991 y el primer tomo se publicó en 1996. ',1000.50,null);")
                .toString());

        bio.execSQL(new StringBuilder("insert into ")
                .append(BaseDatos.PRODUCTOS)
                .append(" values(null,'Libros','Harry potter y la piedra filosofal','Harry Potter y la piedra filosofal, llamado también Harry Potter 1 o abreviado HP1 (título original " +
                        "en inglés, Harry Potter and the Philosopher’s Stone, excepto en Estados Unidos, donde se tituló Harry Potter and the Sorcerer’s Stone), es el primer libro " +
                        "de la serie literaria Harry Potter, escrito por la autora británica J. K. Rowling en 1997, que supuso además el debut de Rowling como escritora profesional.'" +
                        ",500.00,null);")
                .toString());

        bio.execSQL(new StringBuilder("insert into ")
                .append(BaseDatos.PRODUCTOS)
                .append(" values(null,'Libros','Inferno','Inferno es una novela de misterio, ficción y suspense del escritor estadounidense Dan Brown, basada en la simbología oculta en" +
                        " la Divina Comedia, obra clásica de Dante Alighieri, así como en los problemas de la superpoblación mundial. Se trata de la sexta novela del escritor, la cuarta " +
                        "con Robert Langdon como protagonista tras Ángeles y demonios, El código Da Vinci y El símbolo perdido. ',7500.00,null);")
                .toString());

        bio.execSQL(new StringBuilder("insert into ")
                .append(BaseDatos.PRODUCTOS)
                .append(" values(null,'Celulares','Huawei Mate 20 Pro','El Huawei Mate 20 Pro tiene un tamaño de 6,4 pulgadas , con un alto de 152,9 mm y un ancho de 72,5 mm. El grosor del " +
                        "movil es de 8,5 mm. El Mate 20 Pro tiene lector de huellas dactilares. Se trata de un movil resistente gracias a la certificación IP67.',798.00,null);")
                .toString());

        bio.execSQL(new StringBuilder("insert into ")
                .append(BaseDatos.PRODUCTOS)
                .append(" values(null,'Celulares','Samsung Galaxy S9 Plus','Las dimensiones del Samsung Galaxy S9 Plus son: 73,8 milímetros de ancho, 158,1 mm de alto y 8,5 mm de grosor en " +
                        "su perfil, mientras que su peso es de 189 g.tiene un tamaño de pantalla de 6,2 , con una resolución de 2960x1440. La pantalla es de tipo Super AMOLED. Tiene una" +
                        " densidad de píxeles de 570 ppp. Además, con la protección Corning Gorilla Glass 5 te aseguras resistencia frente a arañazos y golpes.',558.00,null);")
                .toString());

        bio.execSQL(new StringBuilder("insert into ")
                .append(BaseDatos.PRODUCTOS)
                .append(" values(null,'Celulares','iPhone Xs','El Apple iPhone XS es el móvil de gama top de Apple de 2018 y llega al mercado con una pantalla OLED panorámica con notch y es " +
                        "el verdadero sucesor del iPhone X. Tiene un diseño compacto, muy similar a su predecesor pero llega con unos marcos más ajustados.El Apple iPhone XS monta una pantalla" +
                        " de 5,8 pulgadas con resolución 2.436x1.125px. Es un panel OLED, la segunda vez que Apple apuesta por esta tecnología y ofrece un nivel de negros óptimo y buena " +
                        "calibración de color. Este panel tiene una densidad de pixeles de 458ppp.',700.00,null);")
                .toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
