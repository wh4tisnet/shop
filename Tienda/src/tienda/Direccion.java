package tienda;

public class Direccion {

    private TipoVia tipoDeVía;
    private String nombre;
    private int numero;
    private String puerta;
    private String ciudad;
    private String provincia;
    private int codigoPostal;

    public TipoVia getTipoDeVía() {
        return tipoDeVía;
    }

    public void setTipoDeVía(TipoVia tipoDeVía) {
        this.tipoDeVía = tipoDeVía;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPuerta() {
        return puerta;
    }

    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}
