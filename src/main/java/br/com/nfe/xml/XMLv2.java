package br.com.nfe.xml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.usicamp.nfe.core.dto.Cobranca;
import br.com.usicamp.nfe.core.dto.Destinatario;
import br.com.usicamp.nfe.core.dto.DetProduto;
import br.com.usicamp.nfe.core.dto.Duplicata;
import br.com.usicamp.nfe.core.dto.Emitente;
import br.com.usicamp.nfe.core.dto.EnvEventoDTO.Evento;
import br.com.usicamp.nfe.core.dto.Identificacao;
import br.com.usicamp.nfe.core.dto.InfAdic;
import br.com.usicamp.nfe.core.dto.Lote;
import br.com.usicamp.nfe.core.dto.NFRef;
import br.com.usicamp.nfe.core.dto.NFe;
import br.com.usicamp.nfe.core.dto.ProcNFe;
import br.com.usicamp.nfe.core.dto.Produto;
import br.com.usicamp.nfe.core.dto.RetEnvEvento;
import br.com.usicamp.nfe.core.dto.ValoresTotais;
import br.com.usicamp.nfe.core.dto.Volumes;
import br.com.usicamp.nfe.core.util.FormatNfe;
import br.inf.portalfiscal.nfe.CancNFeDocument;
import br.inf.portalfiscal.nfe.ConsReciNFeDocument;
import br.inf.portalfiscal.nfe.EnvEventoDocument;
import br.inf.portalfiscal.nfe.EnviNFeDocument;
import br.inf.portalfiscal.nfe.InutNFeDocument;
import br.inf.portalfiscal.nfe.NfeProcDocument;
import br.inf.portalfiscal.nfe.ProcEventoNFeDocument;
import br.inf.portalfiscal.nfe.TAmb;
import br.inf.portalfiscal.nfe.TCOrgaoIBGE;
import br.inf.portalfiscal.nfe.TCancNFe;
import br.inf.portalfiscal.nfe.TCodUfIBGE;
import br.inf.portalfiscal.nfe.TConsReciNFe;
import br.inf.portalfiscal.nfe.TEnderEmi;
import br.inf.portalfiscal.nfe.TEndereco;
import br.inf.portalfiscal.nfe.TEnvEvento;
import br.inf.portalfiscal.nfe.TEnviNFe;
import br.inf.portalfiscal.nfe.TEvento;
import br.inf.portalfiscal.nfe.TEvento.InfEvento;
import br.inf.portalfiscal.nfe.TEvento.InfEvento.DetEvento;
import br.inf.portalfiscal.nfe.TEvento.InfEvento.TpEvento;
import br.inf.portalfiscal.nfe.TInutNFe;
import br.inf.portalfiscal.nfe.TInutNFe.InfInut;
import br.inf.portalfiscal.nfe.TLocal;
import br.inf.portalfiscal.nfe.TNFe;
import br.inf.portalfiscal.nfe.TNFe.InfNFe;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Cobr.Dup;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Dest;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.COFINS;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSNT;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSQtde;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS00;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS10;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS20;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS40;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS51;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS70;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS90;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSPart;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN101;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN102;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN201;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN202;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN500;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN900;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSST;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.IPI;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.PIS;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.PIS.PISAliq;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.PIS.PISNT;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.PIS.PISOutr;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.PIS.PISQtde;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Prod;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Prod.IndTot;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Emit;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Emit.CRT;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Ide;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Ide.NFref;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Transp;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Transp.Transporta;
import br.inf.portalfiscal.nfe.TNfeProc;
import br.inf.portalfiscal.nfe.TProcEvento;
import br.inf.portalfiscal.nfe.TProtNFe;
import br.inf.portalfiscal.nfe.TProtNFe.InfProt;
import br.inf.portalfiscal.nfe.TretEvento;


class XMLv2 implements XmlNFe{
	
	
	XMLv2() {
	}


	public  String getXMLLoteNFe(Lote loteNFe) throws Exception {
		String enviNFeDocumentString = null;
		try {
			EnviNFeDocument enviNFeDocument = EnviNFeDocument.Factory.newInstance();
			TEnviNFe enviNFe = enviNFeDocument.addNewEnviNFe();
			enviNFe.setVersao("2.00");
			enviNFe.setIdLote(loteNFe.getIdLote());
			for (NFe nfe : loteNFe.getListNFe()) {

				TNFe xnfe = enviNFe.addNewNFe();

				InfNFe infNFe = xnfe.addNewInfNFe();
				infNFe.setVersao("2.00");
				infNFe.setId(nfe.getId());
				Ide tagIde = infNFe.addNewIde();
				tagIde.setCUF(br.inf.portalfiscal.nfe.TCodUfIBGE.Enum.forString(nfe.getIdentificacao().getcUF().toString()));
				tagIde.setCNF(nfe.getIdentificacao().getcNF());
				tagIde.setNatOp(nfe.getIdentificacao().getNatOp().toString());
				tagIde.setIndPag(Ide.IndPag.Enum.forString(nfe.getIdentificacao().getIndPag().toString()));
				tagIde.setMod(br.inf.portalfiscal.nfe.TMod.X_55);
				tagIde.setSerie(nfe.getIdentificacao().getSerie().toString());
				tagIde.setNNF(nfe.getIdentificacao().getnNF().toString());
				tagIde.setDEmi(nfe.getIdentificacao().getdEmi());
				if (nfe.getIdentificacao().getdSaiEnt()!= null) {
					tagIde.setDSaiEnt(nfe.getIdentificacao().getdSaiEnt());
				}
				tagIde.setTpNF(Ide.TpNF.Enum.forString(nfe.getIdentificacao().getTpNF().toString()));
				tagIde.setCMunFG(nfe.getIdentificacao().getcMunFG().toString());
				tagIde.setCUF(br.inf.portalfiscal.nfe.TCodUfIBGE.Enum.forString(Integer.toString(nfe.getIdentificacao().getcUF())));
				tagIde.setTpImp(Ide.TpImp.Enum.forInt(nfe.getIdentificacao().getTpImp()));
				tagIde.setTpEmis(Ide.TpEmis.Enum.forInt(nfe.getIdentificacao().getTpEmis()));
				tagIde.setCDV(nfe.getIdentificacao().getcDV().toString());
				tagIde.setTpAmb(br.inf.portalfiscal.nfe.TAmb.Enum.forString(nfe.getIdentificacao().getTpAmb().toString()));
				tagIde.setFinNFe(br.inf.portalfiscal.nfe.TFinNFe.Enum.forString(nfe.getIdentificacao().getFinNFe().toString()));
				
				for(NFRef ref: nfe.getIdentificacao().getNfRef()){
					NFref nfRef = tagIde.addNewNFref();
					nfRef.setRefNFe(ref.getRefNFe());
				}
				
				tagIde.setProcEmi(br.inf.portalfiscal.nfe.TProcEmi.Enum.forString(nfe.getIdentificacao().getProcEmi().toString()));
				tagIde.setVerProc(nfe.getIdentificacao().getVerProc());
				/**
				 * TAG EMITENTE
				 */
				Emit tagEmit = infNFe.addNewEmit();
				tagEmit.setCNPJ(nfe.getEmitente().getCNPJ());
				tagEmit.setXNome(nfe.getEmitente().getxNome());
				tagEmit.setXFant(nfe.getEmitente().getxFant());
				TEnderEmi tagEnderecoEmit = tagEmit.addNewEnderEmit();
				tagEnderecoEmit.setXLgr(nfe.getEmitente().getEndereco().getxLgr());
				tagEnderecoEmit.setNro(nfe.getEmitente().getEndereco().getNro());
				if (nfe.getEmitente().getEndereco().getxCpl() != null) {
					tagEnderecoEmit.setXCpl(nfe.getEmitente().getEndereco().getxCpl());
				}
				tagEnderecoEmit.setXBairro(nfe.getEmitente().getEndereco().getxBairro());
				tagEnderecoEmit.setCMun(nfe.getEmitente().getEndereco().getcMun());
				tagEnderecoEmit.setXMun(nfe.getEmitente().getEndereco().getxMun());
				tagEnderecoEmit.setUF(br.inf.portalfiscal.nfe.TUfEmi.Enum.forString(nfe.getEmitente().getEndereco().getUF()));
				if (nfe.getEmitente().getEndereco().getCEP() != null) {
					tagEnderecoEmit.setCEP(nfe.getEmitente().getEndereco().getCEP());
				}
				tagEnderecoEmit.setCPais(TEnderEmi.CPais.X_1058);
				tagEnderecoEmit.setXPais(TEnderEmi.XPais.BRASIL);
				if (nfe.getEmitente().getEndereco().getFone() != null) {
					tagEnderecoEmit.setFone(nfe.getEmitente().getEndereco().getFone());
				}
				tagEmit.setIE(nfe.getEmitente().getIE());
				if (nfe.getEmitente().getIEST() != null) {
					tagEmit.setIEST(nfe.getEmitente().getIEST());
				}
				if (nfe.getEmitente().getIM() != null) {
					tagEmit.setIM(nfe.getEmitente().getIM());
					tagEmit.setCNAE(nfe.getEmitente().getCNAE());
				}
				//	                if((nfe.getEmitente().getCNAE()!=null)&&(nfe.getEmitente().getIM()!=null)){
				//	                    tagEmit.setCNAE(nfe.getEmitente().getCNAE());
				//	                }
				if(nfe.getEmitente().getCRT()!=null){
					tagEmit.setCRT(CRT.Enum.forInt(nfe.getEmitente().getCRT()));
				}


				/**
				 * TAG DESTINATARIO
				 */

				Dest tagDest = infNFe.addNewDest();
				if (nfe.getDestinatario().getCNPJ() != null) {
					tagDest.setCNPJ(nfe.getDestinatario().getCNPJ());
				} else {
					tagDest.setCPF(nfe.getDestinatario().getCPF());
				}
				tagDest.setXNome(nfe.getDestinatario().getxNome());
				TEndereco tagEndDest = tagDest.addNewEnderDest();
				tagEndDest.setXLgr(nfe.getDestinatario().getEndereco().getxLgr());
				tagEndDest.setNro(nfe.getDestinatario().getEndereco().getNro());
				if ((nfe.getDestinatario().getEndereco().getxCpl() != null)&&(nfe.getDestinatario().getEndereco().getxCpl().length()>0)) {
					tagEndDest.setXCpl(nfe.getDestinatario().getEndereco().getxCpl());
				}

				tagEndDest.setXBairro(nfe.getDestinatario().getEndereco().getxBairro());
				tagEndDest.setCMun(nfe.getDestinatario().getEndereco().getcMun());
				tagEndDest.setXMun(nfe.getDestinatario().getEndereco().getxMun());

				tagEndDest.setUF(br.inf.portalfiscal.nfe.TUf.Enum.forString(nfe.getDestinatario().getEndereco().getUF()));

				if (nfe.getDestinatario().getEndereco().getCEP() != null) {
					tagEndDest.setCEP(nfe.getDestinatario().getEndereco().getCEP());
				}
				if (nfe.getDestinatario().getEndereco().getcPais() != null) {
					tagEndDest.setCPais(br.inf.portalfiscal.nfe.Tpais.Enum.forString(nfe.getDestinatario().getEndereco().getcPais().toString()));
				}
				if (nfe.getDestinatario().getEndereco().getxPais() != null) {
					tagEndDest.setXPais(nfe.getDestinatario().getEndereco().getxPais());
				}
				if (nfe.getDestinatario().getEndereco().getFone() != null) {
					tagEndDest.setFone(nfe.getDestinatario().getEndereco().getFone());
				}

				if(nfe.getDestinatario().getIE()!=null){
					tagDest.setIE(nfe.getDestinatario().getIE());
				}else{
					tagDest.setIE("");
				}

				if (nfe.getDestinatario().getISUF() != null) {
					tagDest.setISUF(nfe.getDestinatario().getISUF());
				}
				/**
				 * Registro G
				 * Informa��o do local de Entrega
				 */
				if (nfe.getLocalEntrega() != null) {
					TLocal localEntrega = infNFe.addNewEntrega();
					localEntrega.setCNPJ(nfe.getLocalEntrega().getCNPJ());
					localEntrega.setXLgr(nfe.getLocalEntrega().getEndereco().getxLgr());
					localEntrega.setNro(nfe.getLocalEntrega().getEndereco().getNro());
					localEntrega.setXCpl(nfe.getLocalEntrega().getEndereco().getxCpl());
					localEntrega.setXBairro(nfe.getLocalEntrega().getEndereco().getxBairro());
					localEntrega.setCMun(nfe.getLocalEntrega().getEndereco().getcMun());
					localEntrega.setXMun(nfe.getLocalEntrega().getEndereco().getxMun());
					localEntrega.setUF(br.inf.portalfiscal.nfe.TUf.Enum.forString(nfe.getLocalEntrega().getEndereco().getUF()));
					//  infnfe.setEntrega(localEntrega);
				}
				/**
				 * H - Detalhamento de produtos e Servi�os da NF-e
				 */

				for (DetProduto detProduto: nfe.getDetProduto()) {

					Det det = infNFe.addNewDet();
					det.setNItem(detProduto.getNumeroItem().toString());
					Prod tagProd = det.addNewProd();
					/**
					 * Produtos e servicos da nfe
					 */
					tagProd.setCProd(detProduto.getProduto().getcProd().toString());
					if (detProduto.getProduto().getCEAN() != null) {
						tagProd.setCEAN(detProduto.getProduto().getCEAN());
					} else {
						tagProd.setCEAN("");
					}
					tagProd.setXProd(detProduto.getProduto().getxProd());

					tagProd.setNCM(detProduto.getProduto().getNCM());

					if (detProduto.getProduto().getEXTIPI() != null) {
						tagProd.setEXTIPI(detProduto.getProduto().getEXTIPI());
					}	
					//if (detProduto.getProduto().getGenero() != null) {
					//    tagProd.setGenero(detProduto.getProduto().getGenero().toString());
					// }
					tagProd.setCFOP(br.inf.portalfiscal.nfe.TCfop.Enum.forString(detProduto.getProduto().getCFOP()));
					tagProd.setUCom(detProduto.getProduto().getuCom());
					tagProd.setQCom(detProduto.getProduto().getqCom());
					tagProd.setVUnCom(detProduto.getProduto().getvUnCom());
					tagProd.setVProd(detProduto.getProduto().getvProd());
					if (detProduto.getProduto().getcEANTrib() != null) {
						tagProd.setCEANTrib(detProduto.getProduto().getcEANTrib());
					} else {
						tagProd.setCEANTrib("");
					}
					tagProd.setUTrib(detProduto.getProduto().getuTrib());
					tagProd.setQTrib(detProduto.getProduto().getqTrib());
					tagProd.setVUnTrib(detProduto.getProduto().getvUnTrib());
					if (detProduto.getProduto().getvFrete() != null) {
						tagProd.setVFrete(detProduto.getProduto().getvFrete());
					}
					if (detProduto.getProduto().getvSeg() != null) {
						tagProd.setVSeg(detProduto.getProduto().getvSeg());
					}
					if (detProduto.getProduto().getvDesc() != null &&
							Double.parseDouble(detProduto.getProduto().getvDesc())>0 ) {
						tagProd.setVDesc(detProduto.getProduto().getvDesc());
					}
					if (detProduto.getProduto().getvOutro()!= null &&
							Double.parseDouble(detProduto.getProduto().getvOutro())>0 ) {
						tagProd.setVOutro(detProduto.getProduto().getvOutro());
					}
					tagProd.setIndTot(IndTot.Enum.forString(detProduto.getProduto().getIndTot().toString()));
					/**
					 *
					 */
					Imposto imposto = det.addNewImposto();
					/**
					 * ICMS
					 */
					ICMS tagICMS = imposto.addNewICMS();


					switch (detProduto.getTributo().getiCMS().getTipoICMS()) {

					case ICMS00:

						break;

					default:
						break;
					}

					switch (detProduto.getTributo().getiCMS().getTipoICMS()) {

					case ICMS00:
						ICMS00 icms00 = tagICMS.addNewICMS00();
						icms00.setOrig(br.inf.portalfiscal.nfe.Torig.Enum.forString((detProduto.getTributo().getiCMS()).getOrigemMercadoria().toString()));
						icms00.setCST(ICMS00.CST.Enum.forString("00"));
						icms00.setModBC(ICMS00.ModBC.Enum.forString((detProduto.getTributo().getiCMS()).getBc().getModBC()));
						icms00.setVBC((detProduto.getTributo().getiCMS()).getBc().getvBC());
						icms00.setPICMS((detProduto.getTributo().getiCMS()).getBc().getpICMS());
						icms00.setVICMS((detProduto.getTributo().getiCMS()).getBc().getvICMS());
						break;
					case ICMS10:
						ICMS10 icms10 = tagICMS.addNewICMS10();
						icms10.setOrig(br.inf.portalfiscal.nfe.Torig.Enum.forString((detProduto.getTributo().getiCMS()).getOrigemMercadoria().toString()));
						icms10.setCST(ICMS10.CST.Enum.forString("10"));
						icms10.setModBC(ICMS10.ModBC.Enum.forString((detProduto.getTributo().getiCMS()).getBc().getModBC()));
						icms10.setVBC((detProduto.getTributo().getiCMS()).getBc().getvBC());
						icms10.setPICMS((detProduto.getTributo().getiCMS()).getBc().getpICMS());
						icms10.setVICMS((detProduto.getTributo().getiCMS()).getBc().getvICMS());
						icms10.setModBCST(ICMS10.ModBCST.Enum.forInt((detProduto.getTributo().getiCMS()).getBcst().getModBCST()));
						if ((detProduto.getTributo().getiCMS().getBcst().getpMVAST() != null)&&(new Double(detProduto.getTributo().getiCMS().getBcst().getpMVAST())>0)) {
							icms10.setPMVAST(detProduto.getTributo().getiCMS().getBcst().getpMVAST());
						}
						if ((detProduto.getTributo().getiCMS().getBcst().getpRedBCST() != null)&&(new Double(detProduto.getTributo().getiCMS().getBcst().getpRedBCST())>0)) {
							icms10.setPRedBCST((detProduto.getTributo().getiCMS()).getBcst().getpRedBCST());
						}
						icms10.setVBCST((detProduto.getTributo().getiCMS()).getBcst().getvBCST());
						icms10.setPICMSST((detProduto.getTributo().getiCMS()).getBcst().getpICMSST());
						icms10.setVICMSST((detProduto.getTributo().getiCMS()).getBcst().getvICMSST());
						break;
					case ICMS20:
						ICMS20 icms20 = tagICMS.addNewICMS20();
						icms20.setOrig(br.inf.portalfiscal.nfe.Torig.Enum.forString((detProduto.getTributo().getiCMS()).getOrigemMercadoria().toString()));
						icms20.setCST(ICMS20.CST.Enum.forString("20"));
						icms20.setModBC(ICMS20.ModBC.Enum.forString((detProduto.getTributo().getiCMS()).getBc().getModBC()));
						icms20.setPRedBC((detProduto.getTributo().getiCMS()).getBc().getpRedBC());
						icms20.setVBC((detProduto.getTributo().getiCMS()).getBc().getvBC());
						icms20.setPICMS((detProduto.getTributo().getiCMS()).getBc().getpICMS());
						icms20.setVICMS((detProduto.getTributo().getiCMS()).getBc().getvICMS());
						break;
					case ICMS30:
						br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS30 icms30 = tagICMS.addNewICMS30();
						icms30.setOrig(br.inf.portalfiscal.nfe.Torig.Enum.forString(detProduto.getTributo().getiCMS().getOrigemMercadoria().toString()));
						icms30.setCST(br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS30.CST.Enum.forString("30"));
						icms30.setModBCST(br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS30.ModBCST.Enum.forInt((detProduto.getTributo().getiCMS()).getBcst().getModBCST()));
						if ((detProduto.getTributo().getiCMS().getBcst().getpMVAST() != null)&&(new Double(detProduto.getTributo().getiCMS().getBcst().getpMVAST())>0)) {
							icms30.setPMVAST((detProduto.getTributo().getiCMS()).getBcst().getpMVAST());
						}
						if ((detProduto.getTributo().getiCMS().getBcst().getpRedBCST() != null)&&(new Double(detProduto.getTributo().getiCMS().getBcst().getpRedBCST())>0)) {
							icms30.setPRedBCST((detProduto.getTributo().getiCMS()).getBcst().getpRedBCST());
						}
						icms30.setVBCST((detProduto.getTributo().getiCMS()).getBcst().getvBCST());
						icms30.setPICMSST((detProduto.getTributo().getiCMS()).getBcst().getpICMSST());
						icms30.setVICMSST((detProduto.getTributo().getiCMS()).getBcst().getvICMSST());
						break;
					case ICMS40:
					case ICMS41:
					case ICMS50:
						ICMS40 icms40 = tagICMS.addNewICMS40();
						icms40.setOrig(br.inf.portalfiscal.nfe.Torig.Enum.forString(detProduto.getTributo().getiCMS().getOrigemMercadoriaString()));
						icms40.setCST(ICMS40.CST.Enum.forString(detProduto.getTributo().getiCMS().getCst()));
						break;
					case ICMS51:
						ICMS51 icms51 = tagICMS.addNewICMS51();
						icms51.setOrig(br.inf.portalfiscal.nfe.Torig.Enum.forString(detProduto.getTributo().getiCMS().getOrigemMercadoria().toString()));
						icms51.setCST(ICMS51.CST.Enum.forString("51"));
						if (detProduto.getTributo().getiCMS().getBc().getModBC() != null) {
							icms51.setModBC(ICMS51.ModBC.Enum.forString((detProduto.getTributo().getiCMS()).getBc().getModBC()));
						}
						if ((detProduto.getTributo().getiCMS().getBc().getpRedBC() != null)&&(new Double(detProduto.getTributo().getiCMS().getBc().getpRedBC()) > 0)) {
							icms51.setPRedBC((detProduto.getTributo().getiCMS()).getBc().getpRedBC());
						}
						if ((detProduto.getTributo().getiCMS().getBc().getvBC() != null)&&(new Double(detProduto.getTributo().getiCMS().getBc().getvBC())>0)) {
							icms51.setVBC((detProduto.getTributo().getiCMS()).getBc().getvBC());
						}
						if ((detProduto.getTributo().getiCMS().getBc().getpICMS() != null)&&(new Double(detProduto.getTributo().getiCMS().getBc().getpICMS())>0)) {
							icms51.setPICMS((detProduto.getTributo().getiCMS()).getBc().getpICMS());
						}
						if ((detProduto.getTributo().getiCMS().getBc().getvICMS() != null)&&(new Double(detProduto.getTributo().getiCMS().getBc().getvICMS())>0)) {
							icms51.setVICMS((detProduto.getTributo().getiCMS()).getBc().getvICMS());
						}
						break;
					case ICMS60:
						br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS60 icms60 = tagICMS.addNewICMS60();
						icms60.setOrig(br.inf.portalfiscal.nfe.Torig.Enum.forString(detProduto.getTributo().getiCMS().getOrigemMercadoria().toString()));
						icms60.setCST(br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS60.CST.Enum.forString(detProduto.getTributo().getiCMS().getCst()));
						{
							String vBCSTRet =(detProduto.getTributo().getiCMS()).getStRet().getvBCSTRet();
							if(vBCSTRet!=null && new Double(vBCSTRet)>0){
								icms60.setVBCSTRet((detProduto.getTributo().getiCMS()).getStRet().getvBCSTRet());
							}
						}
						{
							String VICMSSTRet = (detProduto.getTributo().getiCMS()).getStRet().getvICMSSTRet();
							if(VICMSSTRet!= null && new Double(VICMSSTRet)>0){
								icms60.setVICMSSTRet((detProduto.getTributo().getiCMS()).getStRet().getvICMSSTRet());
							}
						}
						break;
					case ICMS70:
						ICMS70 icms70 = tagICMS.addNewICMS70();
						icms70.setOrig(br.inf.portalfiscal.nfe.Torig.Enum.forString((detProduto.getTributo().getiCMS()).getOrigemMercadoria().toString()));
						icms70.setCST(ICMS70.CST.Enum.forString("70"));
						icms70.setModBC(ICMS70.ModBC.Enum.forString((detProduto.getTributo().getiCMS()).getBc().getModBC()));
						icms70.setPRedBC((detProduto.getTributo().getiCMS()).getBc().getpRedBC());
						icms70.setVBC((detProduto.getTributo().getiCMS()).getBc().getvBC());
						icms70.setPICMS((detProduto.getTributo().getiCMS()).getBc().getpICMS());
						icms70.setVICMS((detProduto.getTributo().getiCMS()).getBc().getvICMS());
						icms70.setModBCST(ICMS70.ModBCST.Enum.forInt((detProduto.getTributo().getiCMS()).getBcst().getModBCST()));
						if ((detProduto.getTributo().getiCMS().getBcst().getpMVAST() != null)&&(new Double(detProduto.getTributo().getiCMS().getBcst().getpMVAST())>0)) {
							icms70.setPMVAST((detProduto.getTributo().getiCMS()).getBcst().getpMVAST());
						}
						if ((detProduto.getTributo().getiCMS().getBcst().getpRedBCST() != null)&&(new Double(detProduto.getTributo().getiCMS().getBcst().getpRedBCST())>0)) {
							icms70.setPRedBCST((detProduto.getTributo().getiCMS()).getBcst().getpRedBCST());
						}
						icms70.setVBCST((detProduto.getTributo().getiCMS()).getBcst().getvBCST());
						icms70.setPICMSST((detProduto.getTributo().getiCMS()).getBcst().getpICMSST());
						icms70.setVICMSST((detProduto.getTributo().getiCMS()).getBcst().getvICMSST());
						break;
					case ICMS90:
						ICMS90 icms90 = tagICMS.addNewICMS90();
						icms90.setOrig(br.inf.portalfiscal.nfe.Torig.Enum.forString((detProduto.getTributo().getiCMS()).getOrigemMercadoria().toString()));
						icms90.setCST(ICMS90.CST.Enum.forString("90"));
						icms90.setModBC(ICMS90.ModBC.Enum.forString((detProduto.getTributo().getiCMS()).getBc().getModBC()));
						if ((detProduto.getTributo().getiCMS().getBc().getpRedBC() != null)&&(new Double(detProduto.getTributo().getiCMS().getBc().getpRedBC()) > 0)) {
							icms90.setPRedBC((detProduto.getTributo().getiCMS()).getBc().getpRedBC());
						}
						icms90.setVBC(detProduto.getTributo().getiCMS().getBc().getvBC());
						icms90.setPICMS((detProduto.getTributo().getiCMS()).getBc().getpICMS());
						icms90.setVICMS((detProduto.getTributo().getiCMS()).getBc().getvICMS());
						icms90.setModBCST(ICMS90.ModBCST.Enum.forInt((detProduto.getTributo().getiCMS()).getBcst().getModBCST()));
						if ((detProduto.getTributo().getiCMS().getBcst().getpMVAST() != null)&&(new Double(detProduto.getTributo().getiCMS().getBcst().getpMVAST())>0)) {
							icms90.setPMVAST((detProduto.getTributo().getiCMS()).getBcst().getpMVAST());
						}
						if ((detProduto.getTributo().getiCMS().getBcst().getpRedBCST() != null)&&(new Double(detProduto.getTributo().getiCMS().getBcst().getpRedBCST())>0)) {
							icms90.setPRedBCST((detProduto.getTributo().getiCMS()).getBcst().getpRedBCST());
						}
						icms90.setVBCST((detProduto.getTributo().getiCMS()).getBcst().getvBCST());
						icms90.setPICMSST((detProduto.getTributo().getiCMS()).getBcst().getpICMSST());
						icms90.setVICMSST((detProduto.getTributo().getiCMS()).getBcst().getvICMSST());
						break;

					case ICMSPart:
						ICMSPart icmsPart = tagICMS.addNewICMSPart();
						icmsPart.setOrig(br.inf.portalfiscal.nfe.Torig.Enum.forString((detProduto.getTributo().getiCMS()).getOrigemMercadoria().toString()));
						icmsPart.setCST(ICMSPart.CST.Enum.forString(detProduto.getTributo().getiCMS().getCst()));
						icmsPart.setModBC(ICMSPart.ModBC.Enum.forString( detProduto.getTributo().getiCMS().getBc().getModBC()));
						icmsPart.setVBC(detProduto.getTributo().getiCMS().getBc().getvBC());
						if ((detProduto.getTributo().getiCMS().getBc().getpRedBC() != null)&&(new Double(detProduto.getTributo().getiCMS().getBc().getpRedBC()) > 0)) {
							icmsPart.setPRedBC((detProduto.getTributo().getiCMS()).getBc().getpRedBC());
						}
						icmsPart.setPICMS((detProduto.getTributo().getiCMS()).getBc().getpICMS());
						icmsPart.setVICMS((detProduto.getTributo().getiCMS()).getBc().getvICMS());
						icmsPart.setModBCST(ICMSPart.ModBCST.Enum.forInt((detProduto.getTributo().getiCMS()).getBcst().getModBCST()));
						if ((detProduto.getTributo().getiCMS().getBcst().getpMVAST() != null)&&(new Double(detProduto.getTributo().getiCMS().getBcst().getpMVAST())>0)) {
							icmsPart.setPMVAST((detProduto.getTributo().getiCMS()).getBcst().getpMVAST());
						}
						if ((detProduto.getTributo().getiCMS().getBcst().getpRedBCST() != null)&&(new Double(detProduto.getTributo().getiCMS().getBcst().getpRedBCST())>0)) {
							icmsPart.setPRedBCST((detProduto.getTributo().getiCMS()).getBcst().getpRedBCST());
						}
						icmsPart.setVBCST((detProduto.getTributo().getiCMS()).getBcst().getvBCST());
						icmsPart.setPICMSST((detProduto.getTributo().getiCMS()).getBcst().getpICMSST());
						icmsPart.setVICMSST((detProduto.getTributo().getiCMS()).getBcst().getvICMSST());

						break;

					case ICMSST:
						ICMSST icmsst = tagICMS.addNewICMSST();
						icmsst.setOrig(br.inf.portalfiscal.nfe.Torig.Enum.forString((detProduto.getTributo().getiCMS()).getOrigemMercadoria().toString()));
						icmsst.setCST(ICMSST.CST.Enum.forString(detProduto.getTributo().getiCMS().getCst()));
						icmsst.setVBCSTRet((detProduto.getTributo().getiCMS()).getStRet().getvBCSTRet());
						icmsst.setVICMSSTRet((detProduto.getTributo().getiCMS()).getStRet().getvICMSSTRet());
						icmsst.setVBCSTDest(detProduto.getTributo().getiCMS().getvBCSTDest());
						icmsst.setVICMSSTDest(detProduto.getTributo().getiCMS().getvICMSSTDest());
						break;

					case ICMSSN101:
						ICMSSN101 icmssn101 = tagICMS.addNewICMSSN101();
						icmssn101.setOrig(br.inf.portalfiscal.nfe.Torig.Enum.forString(detProduto.getTributo().getiCMS().getOrigemMercadoria().toString()));
						icmssn101.setCSOSN(ICMSSN101.CSOSN.Enum.forString(detProduto.getTributo().getiCMS().getCsosn()));
						icmssn101.setPCredSN(detProduto.getTributo().getiCMS().getpCredSN());
						icmssn101.setVCredICMSSN(detProduto.getTributo().getiCMS().getvCredICMSSN());
						break;

					case ICMSSN102:
						ICMSSN102 icmssn102 = tagICMS.addNewICMSSN102();
						icmssn102.setOrig(br.inf.portalfiscal.nfe.Torig.Enum.forString(detProduto.getTributo().getiCMS().getOrigemMercadoria().toString()));
						icmssn102.setCSOSN(ICMSSN102.CSOSN.Enum.forString(detProduto.getTributo().getiCMS().getCsosn()));
						break;

					case ICMSSN201:

						ICMSSN201 icmssn201 = tagICMS.addNewICMSSN201();
						icmssn201.setOrig(br.inf.portalfiscal.nfe.Torig.Enum.forString(detProduto.getTributo().getiCMS().getOrigemMercadoria().toString()));
						icmssn201.setCSOSN(ICMSSN201.CSOSN.Enum.forString(detProduto.getTributo().getiCMS().getCsosn()));
						icmssn201.setModBCST(ICMSSN201.ModBCST.Enum.forInt((detProduto.getTributo().getiCMS()).getBcst().getModBCST()));
						if ((detProduto.getTributo().getiCMS().getBcst().getpMVAST() != null)&&(new Double(detProduto.getTributo().getiCMS().getBcst().getpMVAST())>0)) {
							icmssn201.setPMVAST((detProduto.getTributo().getiCMS()).getBcst().getpMVAST());
						}
						if ((detProduto.getTributo().getiCMS().getBcst().getpRedBCST() != null)&&(new Double(detProduto.getTributo().getiCMS().getBcst().getpRedBCST())>0)) {
							icmssn201.setPRedBCST((detProduto.getTributo().getiCMS()).getBcst().getpRedBCST());
						}
						icmssn201.setVBCST((detProduto.getTributo().getiCMS()).getBcst().getvBCST());
						icmssn201.setPICMSST((detProduto.getTributo().getiCMS()).getBcst().getpICMSST());
						icmssn201.setVICMSST((detProduto.getTributo().getiCMS()).getBcst().getvICMSST());
						icmssn201.setPCredSN(detProduto.getTributo().getiCMS().getpCredSN());
						icmssn201.setVCredICMSSN(detProduto.getTributo().getiCMS().getvCredICMSSN());

						break;

					case ICMSSN202:
						ICMSSN202 icmssn202 = tagICMS.addNewICMSSN202();
						icmssn202.setOrig(br.inf.portalfiscal.nfe.Torig.Enum.forString(detProduto.getTributo().getiCMS().getOrigemMercadoria().toString()));
						icmssn202.setCSOSN(ICMSSN202.CSOSN.Enum.forString(detProduto.getTributo().getiCMS().getCsosn()));
						icmssn202.setModBCST(ICMSSN202.ModBCST.Enum.forInt((detProduto.getTributo().getiCMS()).getBcst().getModBCST()));
						if ((detProduto.getTributo().getiCMS().getBcst().getpMVAST() != null)&&(new Double(detProduto.getTributo().getiCMS().getBcst().getpMVAST())>0)) {
							icmssn202.setPMVAST((detProduto.getTributo().getiCMS()).getBcst().getpMVAST());
						}
						if ((detProduto.getTributo().getiCMS().getBcst().getpRedBCST() != null)&&(new Double(detProduto.getTributo().getiCMS().getBcst().getpRedBCST())>0)) {
							icmssn202.setPRedBCST((detProduto.getTributo().getiCMS()).getBcst().getpRedBCST());
						}
						icmssn202.setVBCST((detProduto.getTributo().getiCMS()).getBcst().getvBCST());
						icmssn202.setPICMSST((detProduto.getTributo().getiCMS()).getBcst().getpICMSST());
						icmssn202.setVICMSST((detProduto.getTributo().getiCMS()).getBcst().getvICMSST());
						break;

					case ICMSSN500:
						ICMSSN500 icmssn500 = tagICMS.addNewICMSSN500();
						icmssn500.setOrig(br.inf.portalfiscal.nfe.Torig.Enum.forString(detProduto.getTributo().getiCMS().getOrigemMercadoria().toString()));
						icmssn500.setCSOSN(ICMSSN500.CSOSN.Enum.forString(detProduto.getTributo().getiCMS().getCsosn()));
						icmssn500.setVBCSTRet((detProduto.getTributo().getiCMS()).getStRet().getvBCSTRet());
						icmssn500.setVICMSSTRet((detProduto.getTributo().getiCMS()).getStRet().getvICMSSTRet());
						break;

					case ICMSSN900:
						ICMSSN900 icmssn900 = tagICMS.addNewICMSSN900();
						icmssn900.setOrig(br.inf.portalfiscal.nfe.Torig.Enum.forString(detProduto.getTributo().getiCMS().getOrigemMercadoria().toString()));
						icmssn900.setCSOSN(ICMSSN900.CSOSN.Enum.forString(detProduto.getTributo().getiCMS().getCsosn()));
						icmssn900.setModBCST(ICMSSN900.ModBCST.Enum.forInt((detProduto.getTributo().getiCMS()).getBcst().getModBCST()));
						icmssn900.setVBC(detProduto.getTributo().getiCMS().getBc().getvBC());
						if ((detProduto.getTributo().getiCMS().getBc().getpRedBC() != null)&&(new Double(detProduto.getTributo().getiCMS().getBc().getpRedBC()) > 0)) {
							icmssn900.setPRedBC((detProduto.getTributo().getiCMS()).getBc().getpRedBC());
						}
						icmssn900.setPICMS((detProduto.getTributo().getiCMS()).getBc().getpICMS());
						icmssn900.setVICMS((detProduto.getTributo().getiCMS()).getBc().getvICMS());
						icmssn900.setModBCST(ICMSSN900.ModBCST.Enum.forInt((detProduto.getTributo().getiCMS()).getBcst().getModBCST()));
						if ((detProduto.getTributo().getiCMS().getBcst().getpMVAST() != null)&&(new Double(detProduto.getTributo().getiCMS().getBcst().getpMVAST())>0)) {
							icmssn900.setPMVAST((detProduto.getTributo().getiCMS()).getBcst().getpMVAST());
						}
						if ((detProduto.getTributo().getiCMS().getBcst().getpRedBCST() != null)&&(new Double(detProduto.getTributo().getiCMS().getBcst().getpRedBCST())>0)) {
							icmssn900.setPRedBCST((detProduto.getTributo().getiCMS()).getBcst().getpRedBCST());
						}
						icmssn900.setVBCST((detProduto.getTributo().getiCMS()).getBcst().getvBCST());
						icmssn900.setPICMSST((detProduto.getTributo().getiCMS()).getBcst().getpICMSST());
						icmssn900.setVICMSST((detProduto.getTributo().getiCMS()).getBcst().getvICMSST());
						icmssn900.setPCredSN(detProduto.getTributo().getiCMS().getpCredSN());
						icmssn900.setVCredICMSSN(detProduto.getTributo().getiCMS().getvCredICMSSN());
						break;

					}




					/**
					 * IPI
					 */

					if (detProduto.getTributo().getIPI() != null) {
						IPI tagIPI = imposto.addNewIPI();
						tagIPI.setCEnq(detProduto.getTributo().getIPI().getcEnq());
						switch (Integer.parseInt(detProduto.getTributo().getIPI().getCST())) {
						case 0:
						case 49:
						case 50:
						case 99:
							IPI.IPITrib trib = tagIPI.addNewIPITrib();
							trib.setCST(IPI.IPITrib.CST.Enum.forString(detProduto.getTributo().getIPI().getCST()));
							if ((detProduto.getTributo().getIPI()).getvBC() != null) {
								trib.setVBC((detProduto.getTributo().getIPI()).getvBC());
								trib.setVIPI((detProduto.getTributo().getIPI()).getvIPI());
							} else {
								trib.setQUnid((detProduto.getTributo().getIPI()).getqUnid());
								trib.setVUnid((detProduto.getTributo().getIPI()).getvUnid());
							}
							trib.setPIPI((detProduto.getTributo().getIPI()).getpIPI());
							break;
						case 01:
						case 02:
						case 03:
						case 04:
						case 05:
						case 51:
						case 52:
						case 53:
						case 54:
						case 55:
							IPI.IPINT nt = tagIPI.addNewIPINT();
							nt.setCST(IPI.IPINT.CST.Enum.forString(detProduto.getTributo().getIPI().getCST().toString()));
							break;
						}
					}
					/**
					 *  PIS
					 */
					PIS tagPIS = imposto.addNewPIS();
					switch (Integer.parseInt(detProduto.getTributo().getpIS().getCST())) {
					case 1:
					case 2:
						PISAliq aliq = tagPIS.addNewPISAliq();	
						aliq.setCST(PISAliq.CST.Enum.forString(detProduto.getTributo().getpIS().getCST().toString()));
						aliq.setVBC(detProduto.getTributo().getpIS().getvBC());
						aliq.setPPIS(detProduto.getTributo().getpIS().getpPIS());
						aliq.setVPIS(detProduto.getTributo().getpIS().getvPIS());
						break;
					case 3:
						PISQtde qtde = tagPIS.addNewPISQtde();
						qtde.setCST(PIS.PISQtde.CST.Enum.forString(detProduto.getTributo().getpIS().getCST().toString()));
						qtde.setQBCProd(detProduto.getTributo().getpIS().getqBCProd());
						qtde.setVAliqProd(detProduto.getTributo().getpIS().getvAliqProd());
						qtde.setVPIS(detProduto.getTributo().getpIS().getvPIS());
						break;
					case 4:
					case 6:
					case 7:
					case 8:
					case 9:
						PISNT pisnt = tagPIS.addNewPISNT();
						pisnt.setCST(PISNT.CST.Enum.forString(detProduto.getTributo().getpIS().getCST().toString()));
						break;
					case 99:
					case 49:
					case 50:
					case 51:
					case 52:
					case 53:
					case 54:
					case 55:
					case 56:	
					case 60:
					case 61:
					case 62:
					case 63:
					case 64:
					case 65:
					case 66:
					case 67:
					case 70:
					case 71:
					case 72:
					case 73:
					case 74:
					case 75:
					case 98:
						PISOutr outr = tagPIS.addNewPISOutr();
						outr.setCST(PISOutr.CST.Enum.forString(detProduto.getTributo().getpIS().getCST().toString()));
						outr.setVBC(detProduto.getTributo().getpIS().getvBC());
						outr.setPPIS(detProduto.getTributo().getpIS().getpPIS());
						outr.setVPIS(detProduto.getTributo().getpIS().getvPIS());

						break;
					default:
						break;
					}
					/**
					 * COFINS
					 */
					COFINS cofins = imposto.addNewCOFINS();

					if (detProduto.getTributo().getCofins() != null) {
						switch (Integer.parseInt(detProduto.getTributo().getCofins().getCST())) {
						case 1:
						case 2:
							COFINSAliq aliq = cofins.addNewCOFINSAliq();
							aliq.setCST(COFINSAliq.CST.Enum.forString(detProduto.getTributo().getCofins().getCST().toString()));
							aliq.setVBC(detProduto.getTributo().getCofins().getvBC());
							aliq.setPCOFINS(detProduto.getTributo().getCofins().getpCOFINS());
							aliq.setVCOFINS(detProduto.getTributo().getCofins().getvCOFINS());
							break;
						case 3:
							COFINSQtde qtde = cofins.addNewCOFINSQtde();
							qtde.setCST(COFINSQtde.CST.Enum.forString(detProduto.getTributo().getCofins().getCST().toString()));
							qtde.setQBCProd(detProduto.getTributo().getCofins().getqBCProd());
							qtde.setVAliqProd(detProduto.getTributo().getCofins().getvAliqProd());
							qtde.setVCOFINS(detProduto.getTributo().getCofins().getvCOFINS());
							break;
						case 4:
						case 6:
						case 7:
						case 8:
						case 9:
							COFINSNT cofinsnt = cofins.addNewCOFINSNT();
							cofinsnt.setCST(COFINSNT.CST.Enum.forString(detProduto.getTributo().getCofins().getCST().toString()));
							break;
						case 99:
						case 49:
						case 50:
						case 51:
						case 52:
						case 53:
						case 54:
						case 55:
						case 56:	
						case 60:
						case 61:
						case 62:
						case 63:
						case 64:
						case 65:
						case 66:
						case 67:
						case 70:
						case 71:
						case 72:
						case 73:
						case 74:
						case 75:
						case 98:
							COFINS.COFINSOutr outr = cofins.addNewCOFINSOutr();
							outr.setCST(COFINS.COFINSOutr.CST.Enum.forString(detProduto.getTributo().getCofins().getCST().toString()));
							outr.setVBC(detProduto.getTributo().getCofins().getvBC());
							outr.setPCOFINS(detProduto.getTributo().getCofins().getpCOFINS());
							outr.setVCOFINS(detProduto.getTributo().getCofins().getvCOFINS());
							break;
						default:
							break;
						}
					}
					/**
					 * Informações adicionais do item
					 */
					if (detProduto.getProduto().getInfAdProd() != null) {
						det.setInfAdProd(detProduto.getProduto().getInfAdProd().getInfAdProd());
					}

				}

				InfNFe.Cobr cobr = infNFe.addNewCobr();

				if((nfe.getCobranca()!=null)&&(nfe.getCobranca().getDuplicata()!=null)){
					for(Duplicata duplicata : nfe.getCobranca().getDuplicata()){
						Dup dup = cobr.addNewDup();
						dup.setNDup(duplicata.getnDup());
						dup.setDVenc(duplicata.getdVenc());
						dup.setVDup(duplicata.getvDup());
					}
				}

				if(nfe.getInfAdic() != null){
					InfNFe.InfAdic infAdic = infNFe.addNewInfAdic();

					if (nfe.getInfAdic().getInfAdFisco() != null) {
						infAdic.setInfAdFisco(nfe.getInfAdic().getInfAdFisco().length()<2000?nfe.getInfAdic().getInfAdFisco():nfe.getInfAdic().getInfAdFisco().substring(0,2000));
					}
					if ((nfe.getInfAdic().getInfCpl() != null)&&(nfe.getInfAdic().getInfCpl().length()>0)) {
						infAdic.setInfCpl(nfe.getInfAdic().getInfCpl().length()<5000?nfe.getInfAdic().getInfCpl():nfe.getInfAdic().getInfCpl().substring(0,5000));
					}
				}

				InfNFe.Total total = infNFe.addNewTotal();
				InfNFe.Total.ICMSTot icmstot = total.addNewICMSTot();

				icmstot.setVBC(nfe.getValoresTotais().getvBC());

				icmstot.setVICMS(nfe.getValoresTotais().getvICMS());

				icmstot.setVBCST(nfe.getValoresTotais().getvBCST());

				icmstot.setVST(nfe.getValoresTotais().getvST());

				icmstot.setVProd(nfe.getValoresTotais().getvProd());


				icmstot.setVFrete(nfe.getValoresTotais().getvFrete());
				icmstot.setVSeg(nfe.getValoresTotais().getvSeg());
				icmstot.setVDesc(nfe.getValoresTotais().getvDesc());
				icmstot.setVII(nfe.getValoresTotais().getvII());
				icmstot.setVIPI(nfe.getValoresTotais().getvIPI());
				icmstot.setVPIS(nfe.getValoresTotais().getvPIS());
				icmstot.setVCOFINS(nfe.getValoresTotais().getvCOFINS());
				icmstot.setVOutro(nfe.getValoresTotais().getvOutro());
				icmstot.setVNF(nfe.getValoresTotais().getvNF());


				Transp transporte = infNFe.addNewTransp();
				transporte.setModFrete(Transp.ModFrete.Enum.forString(nfe.getTransporte().getModFrete().toString()));
				if(nfe.getTransporte().getTransportadora()!=null){
					Transporta transporta = transporte.addNewTransporta();

					if (nfe.getTransporte().getTransportadora().getCNPJ() != null) {
						transporta.setCNPJ(nfe.getTransporte().getTransportadora().getCNPJ());
					} else {
						if (nfe.getTransporte().getTransportadora().getCPF() != null) {
							transporta.setCPF(nfe.getTransporte().getTransportadora().getCPF());
						}
					}
					if (nfe.getTransporte().getTransportadora().getxNome() != null) {
						transporta.setXNome(nfe.getTransporte().getTransportadora().getxNome());
					}
					if (nfe.getTransporte().getTransportadora().getIE() != null) {
						transporta.setIE(nfe.getTransporte().getTransportadora().getIE());
					}
					if (nfe.getTransporte().getTransportadora().getEndereco().getxLgr() != null) {
						transporta.setXEnder(nfe.getTransporte().getTransportadora().getEndereco().getxLgr());
					}
					if (nfe.getTransporte().getTransportadora().getEndereco().getxMun() != null) {
						transporta.setXMun(nfe.getTransporte().getTransportadora().getEndereco().getxMun());
					}
					if (nfe.getTransporte().getTransportadora().getEndereco().getUF() != null) {
						transporta.setUF(br.inf.portalfiscal.nfe.TUf.Enum.forString(nfe.getTransporte().getTransportadora().getEndereco().getUF()));
					}
				}
				if(nfe.getTransporte().getVolumes()!=null){
					for(Volumes volumes : nfe.getTransporte().getVolumes()){
						Transp.Vol vol = transporte.addNewVol();
						if (volumes.getEspecie() != null) {
							vol.setEsp(volumes.getEspecie() );
						}
						if (volumes.getMarca() != null) {
							vol.setMarca(volumes.getMarca());
						}
						if (volumes.getnVol() != null) {
							vol.setNVol(volumes.getnVol());
						}
						if (volumes.getqVol() != null) {
							vol.setQVol(volumes.getqVol());
						}
						if (volumes.getPesoB() != null) {
							vol.setPesoB(volumes.getPesoB());
						}
						if (volumes.getPesoL() != null) {
							vol.setPesoL(volumes.getPesoL());
						}
					}
				}
			}

			enviNFeDocumentString = enviNFeDocument.toString().replaceAll("[\n\r\t]", "");
			enviNFeDocumentString = FormatNfe.retiraEspacoEntreTagXML(enviNFeDocumentString);

			enviNFeDocumentString = FormatNfe.retiraAcentuacao(enviNFeDocumentString);


		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("Erro gera��o do xml : " + ex.getMessage() + " " + ex.getCause().getMessage());
		}

		return enviNFeDocumentString;
	}

	public  String getXmlConsultaLote(String numeroReciboString,Integer ambiente){
		ConsReciNFeDocument consReciNFeDocument = ConsReciNFeDocument.Factory.newInstance();
		TConsReciNFe consReciNFe = consReciNFeDocument.addNewConsReciNFe();
		consReciNFe.setNRec(numeroReciboString);
		consReciNFe.setTpAmb(br.inf.portalfiscal.nfe.TAmb.Enum.forInt(ambiente));
		consReciNFe.setVersao("2.00");
		return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>".concat(consReciNFeDocument.toString()).replaceAll("\n", "")  
		.replaceAll("\r", "")  
		.replaceAll("\t", "")  
		.replaceAll("  ", " ")  
		.replaceAll("  ", " ")  
		.replaceAll("> <", "><")  
		.replaceAll(" </", "></")  
		.replaceAll("> ", ">")  
		.trim(); 
	}

	/**
	 * 
	 *@param  String chaveAcessoString - Chave de acesso <br>
	 *		  Integer ambiente <br>	
	 *		  String numProtocoloString - Numero de protocolo de autorizacao <br>
	 *	      String justificativaString - Justificativa
	*/ 
	public  String getXmlCancNFe(String chaveAcessoString,Integer ambiente,String numProtocoloString,String justificativaString) throws Exception{

		CancNFeDocument cancNFeDocument = CancNFeDocument.Factory.newInstance();
		TCancNFe cancNFe = cancNFeDocument.addNewCancNFe();
		cancNFe.setVersao("2.00");
		TCancNFe.InfCanc infCanc = cancNFe.addNewInfCanc();
		infCanc.setId("ID"+chaveAcessoString);
		infCanc.setTpAmb(br.inf.portalfiscal.nfe.TAmb.Enum.forString(ambiente.toString()));
		infCanc.setXServ(br.inf.portalfiscal.nfe.TCancNFe.InfCanc.XServ.Enum.forString("CANCELAR"));
		infCanc.setChNFe(chaveAcessoString);
		infCanc.setNProt(numProtocoloString);
		infCanc.setXJust(justificativaString);


		return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>".concat(FormatNfe.retiraEspacoEntreTagXML(cancNFeDocument.toString())  )
		.replaceAll("\n", "") 
		.replaceAll("\r", "")  
		.replaceAll("\t", "")  
		.replaceAll("  ", " ")  
		.replaceAll( System.getProperty("line.separator"), "" )
		.replaceAll("> <", "><")  
		.replaceAll(" </", "></")  
		.replaceAll("> ", ">")  
		.trim();

		//	return XMLCancNFeString;*/
	}


	public  String getXmlProcNFe(String xmlNFeString,br.com.usicamp.nfe.core.dto.ProtNfe protNfe) throws Exception{

		NfeProcDocument nfeProcDocument = NfeProcDocument.Factory.newInstance();

		EnviNFeDocument enviNFeDocument = EnviNFeDocument.Factory.parse(xmlNFeString);
		TNfeProc nfeProc = nfeProcDocument.addNewNfeProc();
		nfeProc.setVersao("2.00");
		TNFe xnfe = nfeProc.addNewNFe();

		xnfe.set(enviNFeDocument.getEnviNFe().getNFeArray(0));

		TProtNFe protNFe = nfeProc. addNewProtNFe();

		protNFe.setVersao("2.00");

		InfProt infProt = protNFe.addNewInfProt();
		if(protNfe.getId()!=null){
			infProt.setId(protNfe.getId());
		}
		infProt.setChNFe(protNfe.getChNFe());
		infProt.setCStat(protNfe.getcStat().toString());
		infProt.setVerAplic(protNfe.getVerAplic());
		infProt.setDhRecbto(protNfe.getDhRecbto());
		infProt.setDigVal(protNfe.getDigVal());
		infProt.setNProt(protNfe.getnProt());
		infProt.setTpAmb(br.inf.portalfiscal.nfe.TAmb.Enum.forString(protNfe.getTpAmb().toString()));
		infProt.setVerAplic(protNfe.getVerAplic());
		infProt.setXMotivo(protNfe.getxMotivo());


		return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>".concat(FormatNfe.retiraEspacoEntreTagXML(nfeProcDocument.toString())  )
		.replaceAll("\n", "") 
		.replaceAll("\r", "")  
		.replaceAll("\t", "")  
		.replaceAll("  ", " ")  
		.replaceAll( System.getProperty("line.separator"), "" )
		.replaceAll("> <", "><")  
		.replaceAll(" </", "></")  
		.replaceAll("> ", ">")  
		.trim();

	}

	public  String getXmlInutNFe(br.com.usicamp.nfe.core.dto.InutNFeDTO inutNFeDTO) throws Exception {

		InutNFeDocument inutNFeDocument = InutNFeDocument.Factory.newInstance();
		TInutNFe inutNFe =  inutNFeDocument.addNewInutNFe();
		InfInut infInut = inutNFe.addNewInfInut();
		infInut.setAno(inutNFeDTO.getAno());
		infInut.setCNPJ(inutNFeDTO.getCnpj());
		infInut.setCUF(TCodUfIBGE.Enum.forString(inutNFeDTO.getcUf()));
		infInut.setXServ(br.inf.portalfiscal.nfe.TInutNFe.InfInut.XServ.INUTILIZAR);
		infInut.setSerie(inutNFeDTO.getSerie());

		StringBuffer id = new StringBuffer("ID"); //2
		id.append(inutNFeDTO.getcUf()); //2
		id.append(inutNFeDTO.getAno()); //2
		id.append(inutNFeDTO.getCnpj()); //14
		id.append("55"); //2
		id.append(FormatNfe.getZeroEsqStr(inutNFeDTO.getSerie(),3)); //3
		id.append(FormatNfe.getZeroEsqStr(inutNFeDTO.getNNFIni(),9)); //9
		id.append(FormatNfe.getZeroEsqStr(inutNFeDTO.getNNFFin(),9)); //9

		infInut.setId(id.toString());
		infInut.setTpAmb(br.inf.portalfiscal.nfe.TAmb.Enum.forString(inutNFeDTO.getTpAmb().toString()));
		infInut.setXJust(inutNFeDTO.getxJustificativa());
		
		return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>".concat(FormatNfe.retiraEspacoEntreTagXML(inutNFeDocument.toString())  )
		.replaceAll("\n", "") 
		.replaceAll("\r", "")  
		.replaceAll("\t", "")  
		.replaceAll("  ", " ")  
		.replaceAll( System.getProperty("line.separator"), "" )
		.replaceAll("> <", "><")  
		.replaceAll(" </", "></")  
		.replaceAll("> ", ">")  
		.trim();

	}

	public  String getXmlCartaCorrecao(br.com.usicamp.nfe.core.dto.EnvEventoDTO  envEventoDTO){
		if(envEventoDTO == null)
			return null;
		
		EnvEventoDocument envEventoDocument = EnvEventoDocument.Factory.newInstance();
		
		TEnvEvento tEnvEvento  = envEventoDocument.addNewEnvEvento();
		if(envEventoDTO.getIdLote()!=null)
			tEnvEvento.setIdLote(FormatNfe.getZeroEsqStr(envEventoDTO.getIdLote().toString(), 15));
		
		tEnvEvento.setVersao("1.00");
				
		for(Evento evento:envEventoDTO.getEventos()){
			TEvento tEvento = tEnvEvento.addNewEvento();
			tEvento.setVersao("1.00");
			InfEvento infEvento = tEvento.addNewInfEvento();
			infEvento.setId(evento.getInfEvento().getId());
			if(evento.getInfEvento().getcOrgao()!=null)
				infEvento.setCOrgao(TCOrgaoIBGE.Enum.forString(evento.getInfEvento().getcOrgao().toString()));
			if(evento.getInfEvento().getTpAmb()!=null)
				infEvento.setTpAmb(TAmb.Enum.forString(evento.getInfEvento().getTpAmb().toString()));
			if(evento.getInfEvento().getCNPJ()!=null)
				infEvento.setCNPJ(evento.getInfEvento().getCNPJ());
			infEvento.setChNFe(evento.getInfEvento().getChNFe());
			infEvento.setDhEvento(evento.getInfEvento().getDhEvento());
			infEvento.setTpEvento(TpEvento.X_110110);
			infEvento.setNSeqEvento(evento.getInfEvento().getnSeqEvento());
			infEvento.setVerEvento(InfEvento.VerEvento.X_1_00);
			
			br.inf.portalfiscal.nfe.TEvento.InfEvento.DetEvento detEvento= infEvento.addNewDetEvento();
			detEvento.setVersao(TEvento.InfEvento.DetEvento.Versao.X_1_00);
			detEvento.setDescEvento(DetEvento.DescEvento.CARTA_DE_CORRECAO);
			detEvento.setXCondUso(TEvento.InfEvento.DetEvento.XCondUso.A_CARTA_DE_CORRECAO_E_DISCIPLINADA_PELO_PARAGRAFO_1_O_A_DO_ART_7_O_DO_CONVENIO_S_N_DE_15_DE_DEZEMBRO_DE_1970_E_PODE_SER_UTILIZADA_PARA_REGULARIZACAO_DE_ERRO_OCORRIDO_NA_EMISSAO_DE_DOCUMENTO_FISCAL_DESDE_QUE_O_ERRO_NAO_ESTEJA_RELACIONADO_COM_I_AS_VARIAVEIS_QUE_DETERMINAM_O_VALOR_DO_IMPOSTO_TAIS_COMO_BASE_DE_CALCULO_ALIQUOTA_DIFERENCA_DE_PRECO_QUANTIDADE_VALOR_DA_OPERACAO_OU_DA_PRESTACAO_II_A_CORRECAO_DE_DADOS_CADASTRAIS_QUE_IMPLIQUE_MUDANCA_DO_REMETENTE_OU_DO_DESTINATARIO_III_A_DATA_DE_EMISSAO_OU_DE_SAIDA);
			detEvento.setXCorrecao(evento.getInfEvento().getDetEvento().getxCorrecao());
		} 
		
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>".concat(FormatNfe.retiraEspacoEntreTagXML(envEventoDocument.toString())  )
				.replaceAll("\n", "") 
				.replaceAll("\r", "")  
				.replaceAll("\t", "")  
				.replaceAll("  ", " ")  
				.replaceAll( System.getProperty("line.separator"), "" )
				.replaceAll("> <", "><")  
				.replaceAll(" </", "></")  
				.replaceAll("> ", ">")  
				.trim();
	}

	public String getXmlProcCCe(String xmlEvento,RetEnvEvento.RetEvento retEvento) throws Exception {
		
		ProcEventoNFeDocument procEventoNFeDocument = ProcEventoNFeDocument.Factory.newInstance();
		TProcEvento tProcEvento = procEventoNFeDocument.addNewProcEventoNFe();
		
		tProcEvento.addNewEvento().set(EnvEventoDocument.Factory.parse(xmlEvento));
		
		TretEvento tretEvento = tProcEvento.addNewRetEvento();
		tretEvento.setVersao("1.00");
		br.inf.portalfiscal.nfe.TretEvento.InfEvento infEvento = tretEvento.addNewInfEvento();
		infEvento.setChNFe(retEvento.getInfEvento().getChNFe());
		
		if(retEvento.getInfEvento().getCNPJDest()!=null)
			infEvento.setCNPJDest(retEvento.getInfEvento().getCNPJDest());
		infEvento.setCOrgao(TCOrgaoIBGE.Enum.forString(retEvento.getInfEvento().getcOrgao().toString()));
		
		if(retEvento.getInfEvento().getCPFDest()!=null)
			infEvento.setCPFDest(retEvento.getInfEvento().getCPFDest());
		
		infEvento.setCStat(retEvento.getInfEvento().getcStat().toString());
		infEvento.setEmailDest(retEvento.getInfEvento().getEmailDest());
		
		return procEventoNFeDocument.toString();
	}

	public Lote parseLoteNFe(String xmlLoteNFeString) throws Exception {
		
		EnviNFeDocument enviNFeDocument = EnviNFeDocument.Factory.parse(xmlLoteNFeString);
		Lote lote = new Lote(null);
		
		for (int i = 0; i < enviNFeDocument.getEnviNFe().getNFeArray().length; i++) {
			NFe nfe = getNFe(enviNFeDocument.getEnviNFe().getNFeArray()[i]);
			lote.addNFe(nfe);
		}
		
		return lote;
	}
	
	private NFe getNFe(TNFe tnfe) throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfContingencia = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		SimpleDateFormat sdfHora= new SimpleDateFormat("HH:MM:SS");
		
		Identificacao identificacao = new Identificacao();
		
		if(tnfe.getInfNFe().getIde().getCUF()!=null)
			identificacao.setcUF(Integer.parseInt(tnfe.getInfNFe().getIde().getCUF().toString()));
		
		identificacao.setcNF(tnfe.getInfNFe().getIde().getCNF());
		identificacao.setNatOp(tnfe.getInfNFe().getIde().getNatOp());
		identificacao.setIndPag(Integer.parseInt(tnfe.getInfNFe().getIde().getIndPag().toString()));
		identificacao.setMod(tnfe.getInfNFe().getIde().getMod().toString());
		identificacao.setSerie(Integer.parseInt(tnfe.getInfNFe().getIde().getSerie()));
		identificacao.setnNF(Integer.parseInt(tnfe.getInfNFe().getIde().getNNF()));
		
		if(tnfe.getInfNFe().getIde().getDEmi()!=null)
			identificacao.setdEmi(sdf.parse(tnfe.getInfNFe().getIde().getDEmi()));
		
		if(tnfe.getInfNFe().getIde().getDSaiEnt()!=null)
			identificacao.setdSaiEnt(sdf.parse(tnfe.getInfNFe().getIde().getDSaiEnt()));
		
		if(tnfe.getInfNFe().getIde().getHSaiEnt()!=null)
			identificacao.sethSaiEnt(sdfHora.parse(tnfe.getInfNFe().getIde().getHSaiEnt()));
		
		identificacao.setTpNF(tnfe.getInfNFe().getIde().getTpNF().intValue());
		
		if(tnfe.getInfNFe().getIde().getCMunFG()!=null)
			identificacao.setcMunFG(Integer.parseInt(tnfe.getInfNFe().getIde().getCMunFG()));
		
		if(tnfe.getInfNFe().getIde().getTpEmis()!=null)
			identificacao.setTpEmis(Integer.parseInt(tnfe.getInfNFe().getIde().getTpEmis().toString()));
		
		identificacao.setcDV(Integer.parseInt(tnfe.getInfNFe().getIde().getCDV()));		
		
		if(tnfe.getInfNFe().getIde().getTpAmb()!=null)
			identificacao.setTpAmb(Integer.parseInt(tnfe.getInfNFe().getIde().getTpAmb().toString()));
		
		if(tnfe.getInfNFe().getIde().getFinNFe()!=null)
			identificacao.setFinNFe(Integer.parseInt(tnfe.getInfNFe().getIde().getFinNFe().toString()));
		
		identificacao.setProcEmi(Integer.parseInt(tnfe.getInfNFe().getIde().getProcEmi().toString()));
		identificacao.setVerProc(tnfe.getInfNFe().getIde().getVerProc());
		
		if(tnfe.getInfNFe().getIde().getDhCont() !=null)
			identificacao.setDhCont(sdfContingencia.parse(tnfe.getInfNFe().getIde().getDhCont()));
		
		if(tnfe.getInfNFe().getIde().getXJust()!=null)
			identificacao.setxJust(tnfe.getInfNFe().getIde().getXJust());
		
		Emitente emitente = new Emitente();
		
		if(tnfe.getInfNFe().getEmit().getCNPJ()!=null)
			emitente.setCNPJ(tnfe.getInfNFe().getEmit().getCNPJ());
		
		if(tnfe.getInfNFe().getEmit().getCPF()!=null)
			emitente.setCPF(tnfe.getInfNFe().getEmit().getCPF());
		
		emitente.setxNome(tnfe.getInfNFe().getEmit().getXNome());
		emitente.setxFant(tnfe.getInfNFe().getEmit().getXFant());
		
		emitente.getEndereco().setxLgr(tnfe.getInfNFe().getEmit().getEnderEmit().getXLgr());
		emitente.getEndereco().setNro(tnfe.getInfNFe().getEmit().getEnderEmit().getNro());
		emitente.getEndereco().setxCpl(tnfe.getInfNFe().getEmit().getEnderEmit().getXCpl());
		emitente.getEndereco().setxBairro(tnfe.getInfNFe().getEmit().getEnderEmit().getXBairro());
		emitente.getEndereco().setcMun(Integer.parseInt(tnfe.getInfNFe().getEmit().getEnderEmit().getCMun()));
		emitente.getEndereco().setxMun(tnfe.getInfNFe().getEmit().getEnderEmit().getXMun());
		emitente.getEndereco().setUF(tnfe.getInfNFe().getEmit().getEnderEmit().getUF().toString());
		emitente.getEndereco().setCEP(tnfe.getInfNFe().getEmit().getEnderEmit().getCEP());
		
		if(tnfe.getInfNFe().getEmit().getEnderEmit().getCPais()!=null)
			emitente.getEndereco().setcPais(Integer.parseInt(tnfe.getInfNFe().getEmit().getEnderEmit().getCPais().toString()));
		
		if(tnfe.getInfNFe().getEmit().getEnderEmit().getXPais()!=null)
			emitente.getEndereco().setxPais(tnfe.getInfNFe().getEmit().getEnderEmit().getXPais().toString());
		
		emitente.getEndereco().setFone(tnfe.getInfNFe().getEmit().getEnderEmit().getFone());
		
		emitente.setIE(tnfe.getInfNFe().getEmit().getIE());
		emitente.setIEST(tnfe.getInfNFe().getEmit().getIEST());
		emitente.setIM(tnfe.getInfNFe().getEmit().getIM());
		emitente.setCNAE(tnfe.getInfNFe().getEmit().getCNAE());
		
		if(tnfe.getInfNFe().getEmit().getCRT()!=null)
			emitente.setCRT(Integer.parseInt(tnfe.getInfNFe().getEmit().getCRT().toString()));
		
		Destinatario destinatario = new Destinatario();
		destinatario.setCNPJ(tnfe.getInfNFe().getDest().getCNPJ());
		destinatario.setCPF(tnfe.getInfNFe().getDest().getCPF());
		destinatario.setxNome(tnfe.getInfNFe().getDest().getXNome());
		
		destinatario.getEndereco().setxLgr(tnfe.getInfNFe().getDest().getEnderDest().getXLgr());
		destinatario.getEndereco().setNro(tnfe.getInfNFe().getDest().getEnderDest().getNro());
		destinatario.getEndereco().setxCpl(tnfe.getInfNFe().getDest().getEnderDest().getXCpl());
		destinatario.getEndereco().setxBairro(tnfe.getInfNFe().getDest().getEnderDest().getXBairro());
		destinatario.getEndereco().setcMun(Integer.parseInt(tnfe.getInfNFe().getDest().getEnderDest().getCMun()));
		destinatario.getEndereco().setxMun(tnfe.getInfNFe().getDest().getEnderDest().getXMun());
		
		if(tnfe.getInfNFe().getDest().getEnderDest().getUF()!=null)
			destinatario.getEndereco().setUF(tnfe.getInfNFe().getDest().getEnderDest().getUF().toString());
		
		destinatario.getEndereco().setCEP(tnfe.getInfNFe().getDest().getEnderDest().getCEP());
		
		if(tnfe.getInfNFe().getDest().getEnderDest().getCPais()!=null)
			destinatario.getEndereco().setcPais(Integer.parseInt(tnfe.getInfNFe().getDest().getEnderDest().getCPais().toString()));
		
		destinatario.getEndereco().setxPais(tnfe.getInfNFe().getDest().getEnderDest().getXPais());
		destinatario.getEndereco().setFone(tnfe.getInfNFe().getDest().getEnderDest().getFone());
		
		destinatario.setIE(tnfe.getInfNFe().getDest().getIE());
		destinatario.setISUF(tnfe.getInfNFe().getDest().getISUF());
		destinatario.setEmail(tnfe.getInfNFe().getDest().getEmail());
		
//		DetProduto detProduto = new DetProduto(numeroItem, tributo, produto, infAdProd)
		
		List<DetProduto> detProdutos = new ArrayList<DetProduto>();
		
		for (int j = 0; j < tnfe.getInfNFe().getDetArray().length; j++) {
			 Produto produto = new Produto();
			 Det det = tnfe.getInfNFe().getDetArray()[j];
			 
			 produto.setcProd(det.getProd().getCProd());
			 produto.setCEAN(det.getProd().getCEAN());
			 produto.setxProd(det.getProd().getXProd());
			 produto.setNCM(det.getProd().getNCM());
			 produto.setEXTIPI(det.getProd().getEXTIPI());
			 produto.setCFOP(det.getProd().getCFOP().toString());
			 produto.setuCom(det.getProd().getUCom());
			 
			 if(det.getProd().getQCom()!=null)
				 produto.setqCom(Double.parseDouble(det.getProd().getQCom()));
			 
			 if(det.getProd().getVUnCom()!=null)
				 produto.setvUnCom(Double.parseDouble(det.getProd().getVUnCom()));
			 
			 if(det.getProd().getVProd()!=null)
				 produto.setvProd(Double.parseDouble(det.getProd().getVProd()));
			 
			 produto.setcEANTrib(det.getProd().getCEANTrib());
			 produto.setuTrib(det.getProd().getUTrib());
			 
			 if(det.getProd().getQTrib()!=null)
				 produto.setqTrib(Double.parseDouble(det.getProd().getQTrib()));
			 
			 if(det.getProd().getVUnTrib()!=null)
				 produto.setvUnTrib(Double.parseDouble(det.getProd().getVUnTrib()));
			 
			 if(det.getProd().getVFrete()!=null)
				 produto.setvFrete(Double.parseDouble(det.getProd().getVFrete()));
			 
			 if(det.getProd().getVSeg()!=null)
				 produto.setvSeg(Double.parseDouble(det.getProd().getVSeg()));
			 
			 if(det.getProd().getVDesc()!=null)
				 produto.setvDesc(Double.parseDouble(det.getProd().getVDesc()));
			 
			 if(det.getProd().getVOutro()!=null)
				 produto.setvOutro(Double.parseDouble(det.getProd().getVOutro()));
			 
			 if(det.getProd().getIndTot()!=null)
				 produto.setIndTot(det.getProd().getIndTot().intValue());
			 
			 DetProduto detProduto = new DetProduto(j, null, produto, null);
			 detProdutos.add(detProduto);
			 
		}
		
		ValoresTotais valoresTotais = new ValoresTotais();
		if(tnfe.getInfNFe().getTotal().getICMSTot().getVNF()!=null)
			valoresTotais.setvNF(Double.parseDouble(tnfe.getInfNFe().getTotal().getICMSTot().getVNF()));
		Cobranca cobranca = new Cobranca();
		InfAdic infAdic = new InfAdic("", "");
		
		NFe nfe = new NFe(identificacao, detProdutos, emitente, destinatario, valoresTotais, cobranca, infAdic, null, null, null);
		return nfe;
	}


	public ProcNFe parseProcNFe(String xmlProcNfe) throws Exception {
		
		NfeProcDocument nfeProcDocument = NfeProcDocument.Factory.parse(xmlProcNfe);
		NFe nfe = getNFe( nfeProcDocument.getNfeProc().getNFe() );
		ProcNFe procNFe = new ProcNFe();
		procNFe.setNfe(nfe);
		procNFe.setXmlProcNFe(nfeProcDocument.toString());
		
		return procNFe;
	}

}

