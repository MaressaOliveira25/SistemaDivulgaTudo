package visao;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import modelo.Modeloanuncio;
import modelo.Modelorelatorio;

public class TelaRelatorio implements Initializable {

	@FXML
	private Pane telacad;

	@FXML
	private TextField nomecliente;

	@FXML
	private DatePicker datainicio;

	@FXML
	private DatePicker datafinal;
	Modeloanuncio modelo = new Modeloanuncio();
	Modelorelatorio relatorio = new Modelorelatorio();
	String erro = "";
	String relatoriogerado;
	boolean Valida = true;

	@FXML
	void Gerarelatorio(MouseEvent event) {
		if ((nomecliente.getText() == null) && (datainicio.getValue() == null) && (datafinal.getValue() == null)) {
			erro = erro + "\n Preenchimento obrigat�rio do nome do cliente, ou do intervalo de datas";
			Valida = false;
		} else {
			if (nomecliente.getText() != null) {
				if (!modelo.Validarnome(nomecliente.getText())) {
					erro = erro + "\n Preencha o nome do cliente com no m�nimo 4 caracteres";
					Valida = false;
				}
			}
			if (datainicio.getValue() != null) {
				if (datafinal.getValue() != null) {
					if (!modelo.Validarfinal(Date.valueOf(datainicio.getValue()), Date.valueOf(datafinal.getValue()))) {
						erro = erro + "\n A data final n�o pode ser menor ou igual � data inicial.";

						Valida = false;
					}
				} else {
					erro = erro + "\n Selecione a data final do an�ncio.";
					Valida = false;
				}
			}
			if (datafinal.getValue() != null) {
				if (datainicio.getValue() == null) {
					erro = erro + "\n Informe a data de in�cio!";
					Valida = false;
				}
			}
		}

		if (Valida) {
			ArrayList<Modeloanuncio> resultado = null;
			if ((datainicio.getValue() != null) && (datafinal.getValue() != null)) {
				resultado = modelo.consultadata(Date.valueOf(datainicio.getValue()),
						Date.valueOf(datafinal.getValue()));

			} else {
				resultado = modelo.consultanome(nomecliente.getText());
			}
			if (resultado.size() != 0) {
				for (Modeloanuncio anuncioatual : resultado) {
					relatoriogerado = relatoriogerado + "\n O An�ncio " + anuncioatual.getNomeanuncio();
					relatoriogerado = relatoriogerado + "\n Do(a) " + anuncioatual.getNomecliente();
					relatorio.setValorinvestido(
							modelo.Diferencaentredatas(anuncioatual.getDatainicio(), anuncioatual.getDatafinal())
									* anuncioatual.getInvestimentodia());
					System.out.println(anuncioatual.getInvestimentodia());
					relatoriogerado = relatoriogerado + "\n O valor total do investimento � de R$ "
							+ relatorio.getValorinvestido();
					relatorio.Calcular();
					relatoriogerado = relatoriogerado + "\n Quantidade m�xima de visualiza��es: "
							+ relatorio.getQuantidademaximavisualizacoes();
					relatoriogerado = relatoriogerado + "\n Quantidade m�xima de cliques: "
							+ relatorio.getQuantidademaximadecliques();
					relatoriogerado = relatoriogerado + "\n Quantidade m�xima de compartilhamentos: "
							+ relatorio.getQuantidademaximadecompartilhamentos();
				}
				Alert dialogoInfo = new Alert(Alert.AlertType.CONFIRMATION);
				dialogoInfo.setTitle("Relat�rio");
				dialogoInfo.setContentText(relatoriogerado);
				dialogoInfo.showAndWait();
			}
			else {
				Alert dialogoInfo = new Alert(Alert.AlertType.WARNING);
				dialogoInfo.setTitle("Alerta");
				dialogoInfo.setContentText("N�o foram encontrados dados com o(s) par�metro(s) informado(s)!");
				dialogoInfo.showAndWait();
			}
			relatoriogerado = "";
			nomecliente.setText(null);
			datainicio.setValue(null);
			datafinal.setValue(null);

		} else {
			Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
			dialogoInfo.setTitle("Mensagem de erro");
			dialogoInfo.setContentText(erro);
			dialogoInfo.showAndWait();
		}
		erro = "";
		Valida = true;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		nomecliente.setText(null);
		datainicio.setValue(null);
		datafinal.setValue(null);
	}

}
