package es.netmind.mypersonalbankapi.modelos.clientes;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OrderColumn;
import java.time.LocalDate;
import java.util.Arrays;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Schema(name = "Empresa", description = "Datos cliente empresa")
public class Empresa extends Cliente {
    @Schema(name = "Cif", example = "B12345678", required = true)
    private String cif;
    @ElementCollection
    @OrderColumn
    @Column(name = "unidades_de_negocio")
    @Schema(name = "Unidades de Negocio", example = "UN 1", required = true)
    private String[] unidadesNegocio;

    public Empresa(Integer id, String nombre, String email, String direccion, LocalDate alta, boolean activo, boolean moroso, String cif, String[] unidadesNegocio) throws Exception{
        super(id, nombre, email, direccion, alta, activo, moroso);
        setCif(cif);
        this.unidadesNegocio = unidadesNegocio;
    }

    private boolean validarCIF(String cif) throws Exception{
        if (cif != null && cif.length() == 9) {
            String intPartCIF = cif.trim().replaceAll(" ", "").substring(1, 9);
            char ltrCIF = cif.charAt(0);
            int valNumCif = Integer.parseInt(intPartCIF);
            return valNumCif > 0 || !Character.isLetter(ltrCIF);
        } else return false;
    }

    @Override
    public boolean validar() throws Exception{
        return this.validarComun() && validarCIF(this.cif);
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) throws Exception{
        if(validarCIF(cif)) this.cif = cif;
    }

    public String[] getUnidadesNegocio() {
        return unidadesNegocio;
    }

    public void setUnidadesNegocio(String[] unidadesNegocio) {
        this.unidadesNegocio = unidadesNegocio;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "cif='" + cif + '\'' +
                ", unidadesNegocio=" + Arrays.toString(unidadesNegocio) +
                "} " + super.toString();
    }
}
