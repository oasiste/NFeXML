package br.com.nfe.xml;

import br.com.usicamp.nfe.core.dto.EnvEventoDTO;
import br.com.usicamp.nfe.core.dto.InutNFeDTO;
import br.com.usicamp.nfe.core.dto.Lote;
import br.com.usicamp.nfe.core.dto.ProcNFe;
import br.com.usicamp.nfe.core.dto.ProtNfe;
import br.com.usicamp.nfe.core.dto.RetEnvEvento;

public interface XmlNFe {
	String getXMLLoteNFe(Lote loteNFe) throws Exception;
	String getXmlConsultaLote(String numeroReciboString,Integer ambiente)throws Exception;
	String getXmlCancNFe(String chaveAcessoString,Integer ambiente,String numProtocoloString,String justificativaString) throws Exception;
	String getXmlProcNFe(String xmlNFeString,ProtNfe protNfe)throws Exception;
	String getXmlInutNFe(InutNFeDTO inutNFeDTO) throws Exception;
	String getXmlCartaCorrecao(EnvEventoDTO envEventoDTO) throws Exception;
	String getXmlProcCCe(String xmlProcCCe,RetEnvEvento.RetEvento retEvento) throws Exception;
	Lote parseLoteNFe(String xmlLoteNFeString) throws Exception;
	ProcNFe parseProcNFe(String xmlProcNfe) throws Exception;
}
