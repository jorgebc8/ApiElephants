package com.example.apielephants.modelo.modelo;

//Creo la clase Usuario donde guardo las variables que tendra el usuario junto con su constructor y los métodos
//correspondientes

public class Usuario {
    int id;
    String Usuario, Contraseña, Nombre, Apellidos;

    public Usuario() {
    }
// Generamos el constructor sin el ID ya que el id se autoincrementa
    public Usuario(String usuario, String contraseña, String nombre, String apellidos) {
        Usuario = usuario;
        Contraseña = contraseña;
        Nombre = nombre;
        Apellidos = apellidos;
    }
    //Generamos un método para verificar que los campos no esten vacios

    public boolean isNull(){
        if(Usuario.equals("") || Contraseña.equals("") || Nombre.equals("") || Apellidos.equals("")){
            return false;
        }else{
            return true;
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", Usuario='" + Usuario + '\'' +
                ", Contraseña='" + Contraseña + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", Apellidos='" + Apellidos + '\'' +
                '}';
    }
}
