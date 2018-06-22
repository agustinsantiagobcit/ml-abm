<?xml version="1.0" encoding="utf-8"?>
<wsdl:definitions xmlns:tm="http://microsoft.com/wsdl/mime/textMatching/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:tns="http://preingreso.metlife.cl/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:s1="http://microsoft.com/wsdl/types/" xmlns:s="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" targetNamespace="http://preingreso.metlife.cl/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <wsdl:types>
    <s:schema elementFormDefault="qualified" targetNamespace="http://preingreso.metlife.cl/">
      <s:import namespace="http://microsoft.com/wsdl/types/" />
      <s:element name="Crear">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="sesionId" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="tipoMovimiento" type="s:int" />
            <s:element minOccurs="0" maxOccurs="1" name="nombreAdjunto" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="fechaRecepcion" nillable="true" type="s:dateTime" />
            <s:element minOccurs="0" maxOccurs="1" name="portalRut" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="portalNombre" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="agrupacionCorredor" type="tns:AgrupacionCorredor" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="AgrupacionCorredor">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="RutDv" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Nombre" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="CrearResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CrearResult" type="tns:BulkBo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="BulkBo">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="Id" nillable="true" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="EstadoId" nillable="true" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="Error" type="tns:ErrorBo" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ErrorBo">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="Numero" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="Descripcion" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="CrearAlta">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="sesionId" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="movto" type="tns:MovtoAltaBo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="MovtoAltaBo">
        <s:complexContent mixed="false">
          <s:extension base="tns:MovtoBo">
            <s:sequence>
              <s:element minOccurs="1" maxOccurs="1" name="FechaInicioVigencia" type="s:dateTime" />
              <s:element minOccurs="1" maxOccurs="1" name="FechaTerminoVigencia" nillable="true" type="s:dateTime" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="MovtoBo">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="BulkId" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="PolizaNumero" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="GrupoId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="GrupoNombre" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Lote" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Barcode" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FileNetCodigo" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="MetlifeCodigo" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Titular" type="tns:TitularBo" />
          <s:element minOccurs="0" maxOccurs="1" name="Carga" type="tns:CargaBo" />
          <s:element minOccurs="0" maxOccurs="1" name="Corredor" type="tns:CorredorBo" />
          <s:element minOccurs="0" maxOccurs="1" name="Error" type="tns:ErrorBo" />
          <s:element minOccurs="0" maxOccurs="1" name="Set" type="tns:SetBo" />
          <s:element minOccurs="1" maxOccurs="1" name="DetalleNumero" nillable="true" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="Estado" type="tns:EstadoBo" />
          <s:element minOccurs="0" maxOccurs="1" name="PortalRut" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="PortalUsuario" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="TitularBo">
        <s:complexContent mixed="false">
          <s:extension base="tns:PersonaBo">
            <s:sequence>
              <s:element minOccurs="1" maxOccurs="1" name="Capital" nillable="true" type="s:double" />
              <s:element minOccurs="1" maxOccurs="1" name="Renta" nillable="true" type="s:double" />
              <s:element minOccurs="0" maxOccurs="1" name="Email" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Celular" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="CuentaDeposito" type="tns:CuentaDepositoBo" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="PersonaBo">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="Id" nillable="true" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="Rut" nillable="true" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="Dv" nillable="true" type="s1:char" />
          <s:element minOccurs="0" maxOccurs="1" name="Nombre" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ApellidoPaterno" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ApellidoMaterno" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FechaNacimiento" nillable="true" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="Sexo" nillable="true" type="s1:char" />
          <s:element minOccurs="1" maxOccurs="1" name="Peso" nillable="true" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="Estatura" nillable="true" type="s:double" />
          <s:element minOccurs="0" maxOccurs="1" name="PreExistencia" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="CargaBo">
        <s:complexContent mixed="false">
          <s:extension base="tns:PersonaBo">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="Relacion" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="CuentaDepositoBo">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="BancoId" nillable="true" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="BancoNombre" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Tipo" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Numero" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="CorredorBo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="RutDv" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Nombre" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="SetBo">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="Id" nillable="true" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="Folio" nillable="true" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="EstadoId" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="EstadoNombre" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Contratante" type="tns:ContratanteBo" />
          <s:element minOccurs="0" maxOccurs="1" name="TipoMovimiento" type="tns:TipoMovimientoBo" />
          <s:element minOccurs="0" maxOccurs="1" name="MovtoAltas" type="tns:ArrayOfMovtoAltaBo" />
          <s:element minOccurs="0" maxOccurs="1" name="MovtoBajas" type="tns:ArrayOfMovtoBajaBo" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ContratanteBo">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="Rut" nillable="true" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="Dv" nillable="true" type="s1:char" />
          <s:element minOccurs="0" maxOccurs="1" name="Nombre" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="TipoMovimientoBo">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="Id" nillable="true" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="Nombre" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfMovtoAltaBo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="MovtoAltaBo" nillable="true" type="tns:MovtoAltaBo" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfMovtoBajaBo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="MovtoBajaBo" nillable="true" type="tns:MovtoBajaBo" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="MovtoBajaBo">
        <s:complexContent mixed="false">
          <s:extension base="tns:MovtoBo">
            <s:sequence>
              <s:element minOccurs="1" maxOccurs="1" name="FechaBaja" nillable="true" type="s:dateTime" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="EstadoBo">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="Id" nillable="true" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="Descripcion" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="CrearAltaResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CrearAltaResult" type="tns:ErrorBo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CrearBaja">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="sesionId" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="movto" type="tns:MovtoBajaBo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CrearBajaResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CrearBajaResult" type="tns:ErrorBo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Ejecutar">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="sesionId" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="bulkId" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="EjecutarResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="EjecutarResult" type="tns:ErrorBo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Info">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="sesionId" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="bulkId" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="InfoResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="InfoResult" type="tns:BulkBo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="ListarError">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="sesionId" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="bulkId" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="ListarErrorResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="ListarErrorResult" type="tns:ArrayOfMovtoErrorBo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="ArrayOfMovtoErrorBo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="MovtoErrorBo" nillable="true" type="tns:MovtoErrorBo" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="MovtoErrorBo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="Titular" type="tns:TitularBo" />
          <s:element minOccurs="0" maxOccurs="1" name="Carga" type="tns:CargaBo" />
          <s:element minOccurs="0" maxOccurs="1" name="Error" type="tns:ErrorBo" />
        </s:sequence>
      </s:complexType>
      <s:element name="ListarSet">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="sesionId" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="bulkId" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="ListarSetResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="ListarSetResult" type="tns:ArrayOfSetBo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="ArrayOfSetBo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="SetBo" nillable="true" type="tns:SetBo" />
        </s:sequence>
      </s:complexType>
      <s:element name="InfoSetBajas">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="sesionId" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="bulkId" nillable="true" type="s:int" />
            <s:element minOccurs="1" maxOccurs="1" name="setId" nillable="true" type="s:int" />
            <s:element minOccurs="0" maxOccurs="1" name="lote" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="fechaDesde" nillable="true" type="s:dateTime" />
            <s:element minOccurs="1" maxOccurs="1" name="fechaHasta" nillable="true" type="s:dateTime" />
            <s:element minOccurs="0" maxOccurs="1" name="corredorRutDv" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="login" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="InfoSetBajasResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="InfoSetBajasResult" type="tns:ArrayOfMovtoBajaBo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="FileNetCodigoGrabar">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="sesionId" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="bulkId" type="s:int" />
            <s:element minOccurs="1" maxOccurs="1" name="polizaNumero" type="s:int" />
            <s:element minOccurs="1" maxOccurs="1" name="grupo" type="s:int" />
            <s:element minOccurs="1" maxOccurs="1" name="titularRut" type="s:int" />
            <s:element minOccurs="1" maxOccurs="1" name="titularDv" type="s1:char" />
            <s:element minOccurs="1" maxOccurs="1" name="cargaRut" nillable="true" type="s:int" />
            <s:element minOccurs="1" maxOccurs="1" name="cargaDv" nillable="true" type="s1:char" />
            <s:element minOccurs="0" maxOccurs="1" name="codigoFileNet" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="errorNumero" type="s:int" />
            <s:element minOccurs="0" maxOccurs="1" name="errorDescripcion" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="FileNetCodigoGrabarResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="FileNetCodigoGrabarResult" type="tns:ErrorBo" />
            <s:element minOccurs="1" maxOccurs="1" name="errorNumero" type="s:int" />
            <s:element minOccurs="0" maxOccurs="1" name="errorDescripcion" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="BulkDetalle2">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="sesionId" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="bulkId" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="BulkDetalle2Response">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="BulkDetalle2Result" type="tns:BulkDetalleBo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="BulkDetalleBo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="Error" type="tns:ErrorBo" />
          <s:element minOccurs="0" maxOccurs="1" name="Detalles" type="tns:ArrayOfDetalleBo" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfDetalleBo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="DetalleBo" nillable="true" type="tns:DetalleBo" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="DetalleBo">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="BulkId" nillable="true" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="Correlativo" nillable="true" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="PolizaNumero" nillable="true" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="Grupo" nillable="true" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="FechaInicioVigencia" nillable="true" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="FechaTerminoVigencia" nillable="true" type="s:dateTime" />
          <s:element minOccurs="0" maxOccurs="1" name="Titular" type="tns:TitularBo" />
          <s:element minOccurs="0" maxOccurs="1" name="Carga" type="tns:CargaBo" />
          <s:element minOccurs="1" maxOccurs="1" name="Lote" nillable="true" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="Estado" type="tns:EstadoBo" />
          <s:element minOccurs="0" maxOccurs="1" name="CodigoBarra" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FileNetCodigo" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="MetlifeCodigo" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Corredor" type="tns:CorredorBo" />
          <s:element minOccurs="0" maxOccurs="1" name="Errores" type="tns:ArrayOfErrorBo" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfErrorBo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="ErrorBo" nillable="true" type="tns:ErrorBo" />
        </s:sequence>
      </s:complexType>
      <s:element name="BulkDetalle">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="sesionId" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="bulkId" nillable="true" type="s:int" />
            <s:element minOccurs="1" maxOccurs="1" name="fechaDesde" nillable="true" type="s:dateTime" />
            <s:element minOccurs="1" maxOccurs="1" name="fechaHasta" nillable="true" type="s:dateTime" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="BulkDetalleResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="BulkDetalleResult" type="tns:BulkDetalleBo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CrearAltaDm">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="sesionId" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="MovtoAlta" type="tns:DmMovtoAltaBo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="DmMovtoAltaBo">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="BulkId" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="PolizaNumero" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="GrupoNumero" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="Titular" type="tns:DmTitularBo" />
          <s:element minOccurs="0" maxOccurs="1" name="Carga" type="tns:DmCargaBo" />
          <s:element minOccurs="0" maxOccurs="1" name="Observacion" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="TipoPersona" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="TipoBeneficiario" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="Porcentaje" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="FechaInicioVigencia" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="FechaTerminoVigencia" nillable="true" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="Capital" nillable="true" type="s:double" />
          <s:element minOccurs="0" maxOccurs="1" name="Renta" type="tns:DmRentaBo" />
          <s:element minOccurs="0" maxOccurs="1" name="Tercero" type="tns:DmTerceroBo" />
          <s:element minOccurs="0" maxOccurs="1" name="Cobranza" type="tns:DmCobranzaBo" />
          <s:element minOccurs="0" maxOccurs="1" name="Venta" type="tns:DmVentaBo" />
          <s:element minOccurs="0" maxOccurs="1" name="PortalRut" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="PortalUsuario" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Corredor" type="tns:DmCorredorBo" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="DmTitularBo">
        <s:complexContent mixed="false">
          <s:extension base="tns:DmPersonaBo">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="Email" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="CuentaDeposito" type="tns:DmCuentaDepositoBo" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DmPersonaBo">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="Rut" nillable="true" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="Dv" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Nombre" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ApellidoPaterno" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ApellidoMaterno" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Sexo" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FechaNacimiento" nillable="true" type="s:dateTime" />
          <s:element minOccurs="0" maxOccurs="1" name="Isapre" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Direccion" type="tns:DmDireccionBo" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="DmDireccionBo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="Ubicacion" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Comuna" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Ciudad" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Region" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Telefono" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="DmCargaBo">
        <s:complexContent mixed="false">
          <s:extension base="tns:DmPersonaBo">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="Relacion" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DmCuentaDepositoBo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="Banco" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Numero" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="VigenciaTc" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="DmRentaBo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="Moneda" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="Renta" nillable="true" type="s:double" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="DmTerceroBo">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="Rut" nillable="true" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="Dv" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="NombreCompleto" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Direccion" type="tns:DmDireccionBo" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="DmCobranzaBo">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="Dia" nillable="true" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="Mes" nillable="true" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="Llave" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="DmVentaBo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="Propuesta" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="Fecha" nillable="true" type="s:dateTime" />
          <s:element minOccurs="0" maxOccurs="1" name="Supervisor" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Ejecutivo" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="DmCorredorBo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="RutDv" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Nombre" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="CrearAltaDmResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CrearAltaDmResult" type="tns:ErrorBo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CrearBajaDm">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="sesionId" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="MovtoBaja" type="tns:DmMovtoBajaBo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="DmMovtoBajaBo">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="BulkId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="PolizaNumero" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="GrupoNumero" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="Titular" type="tns:DmTitularBo" />
          <s:element minOccurs="0" maxOccurs="1" name="Carga" type="tns:DmCargaBo" />
          <s:element minOccurs="1" maxOccurs="1" name="FechaBaja" nillable="true" type="s:dateTime" />
          <s:element minOccurs="0" maxOccurs="1" name="PortalRut" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="PortalUsuario" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Corredor" type="tns:DmCorredorBo" />
        </s:sequence>
      </s:complexType>
      <s:element name="CrearBajaDmResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CrearBajaDmResult" type="tns:ErrorBo" />
          </s:sequence>
        </s:complexType>
      </s:element>
    </s:schema>
    <s:schema elementFormDefault="qualified" targetNamespace="http://microsoft.com/wsdl/types/">
      <s:simpleType name="char">
        <s:restriction base="s:unsignedShort" />
      </s:simpleType>
    </s:schema>
  </wsdl:types>
  <wsdl:message name="CrearSoapIn">
    <wsdl:part name="parameters" element="tns:Crear" />
  </wsdl:message>
  <wsdl:message name="CrearSoapOut">
    <wsdl:part name="parameters" element="tns:CrearResponse" />
  </wsdl:message>
  <wsdl:message name="CrearAltaSoapIn">
    <wsdl:part name="parameters" element="tns:CrearAlta" />
  </wsdl:message>
  <wsdl:message name="CrearAltaSoapOut">
    <wsdl:part name="parameters" element="tns:CrearAltaResponse" />
  </wsdl:message>
  <wsdl:message name="CrearBajaSoapIn">
    <wsdl:part name="parameters" element="tns:CrearBaja" />
  </wsdl:message>
  <wsdl:message name="CrearBajaSoapOut">
    <wsdl:part name="parameters" element="tns:CrearBajaResponse" />
  </wsdl:message>
  <wsdl:message name="EjecutarSoapIn">
    <wsdl:part name="parameters" element="tns:Ejecutar" />
  </wsdl:message>
  <wsdl:message name="EjecutarSoapOut">
    <wsdl:part name="parameters" element="tns:EjecutarResponse" />
  </wsdl:message>
  <wsdl:message name="InfoSoapIn">
    <wsdl:part name="parameters" element="tns:Info" />
  </wsdl:message>
  <wsdl:message name="InfoSoapOut">
    <wsdl:part name="parameters" element="tns:InfoResponse" />
  </wsdl:message>
  <wsdl:message name="ListarErrorSoapIn">
    <wsdl:part name="parameters" element="tns:ListarError" />
  </wsdl:message>
  <wsdl:message name="ListarErrorSoapOut">
    <wsdl:part name="parameters" element="tns:ListarErrorResponse" />
  </wsdl:message>
  <wsdl:message name="ListarSetSoapIn">
    <wsdl:part name="parameters" element="tns:ListarSet" />
  </wsdl:message>
  <wsdl:message name="ListarSetSoapOut">
    <wsdl:part name="parameters" element="tns:ListarSetResponse" />
  </wsdl:message>
  <wsdl:message name="InfoSetBajasSoapIn">
    <wsdl:part name="parameters" element="tns:InfoSetBajas" />
  </wsdl:message>
  <wsdl:message name="InfoSetBajasSoapOut">
    <wsdl:part name="parameters" element="tns:InfoSetBajasResponse" />
  </wsdl:message>
  <wsdl:message name="FileNetCodigoGrabarSoapIn">
    <wsdl:part name="parameters" element="tns:FileNetCodigoGrabar" />
  </wsdl:message>
  <wsdl:message name="FileNetCodigoGrabarSoapOut">
    <wsdl:part name="parameters" element="tns:FileNetCodigoGrabarResponse" />
  </wsdl:message>
  <wsdl:message name="BulkDetalle2SoapIn">
    <wsdl:part name="parameters" element="tns:BulkDetalle2" />
  </wsdl:message>
  <wsdl:message name="BulkDetalle2SoapOut">
    <wsdl:part name="parameters" element="tns:BulkDetalle2Response" />
  </wsdl:message>
  <wsdl:message name="BulkDetalleSoapIn">
    <wsdl:part name="parameters" element="tns:BulkDetalle" />
  </wsdl:message>
  <wsdl:message name="BulkDetalleSoapOut">
    <wsdl:part name="parameters" element="tns:BulkDetalleResponse" />
  </wsdl:message>
  <wsdl:message name="CrearAltaDmSoapIn">
    <wsdl:part name="parameters" element="tns:CrearAltaDm" />
  </wsdl:message>
  <wsdl:message name="CrearAltaDmSoapOut">
    <wsdl:part name="parameters" element="tns:CrearAltaDmResponse" />
  </wsdl:message>
  <wsdl:message name="CrearBajaDmSoapIn">
    <wsdl:part name="parameters" element="tns:CrearBajaDm" />
  </wsdl:message>
  <wsdl:message name="CrearBajaDmSoapOut">
    <wsdl:part name="parameters" element="tns:CrearBajaDmResponse" />
  </wsdl:message>
  <wsdl:portType name="WsBulkSoap">
    <wsdl:operation name="Crear">
      <wsdl:input message="tns:CrearSoapIn" />
      <wsdl:output message="tns:CrearSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="CrearAlta">
      <wsdl:input message="tns:CrearAltaSoapIn" />
      <wsdl:output message="tns:CrearAltaSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="CrearBaja">
      <wsdl:input message="tns:CrearBajaSoapIn" />
      <wsdl:output message="tns:CrearBajaSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Ejecutar">
      <wsdl:input message="tns:EjecutarSoapIn" />
      <wsdl:output message="tns:EjecutarSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Info">
      <wsdl:input message="tns:InfoSoapIn" />
      <wsdl:output message="tns:InfoSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="ListarError">
      <wsdl:input message="tns:ListarErrorSoapIn" />
      <wsdl:output message="tns:ListarErrorSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="ListarSet">
      <wsdl:input message="tns:ListarSetSoapIn" />
      <wsdl:output message="tns:ListarSetSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="InfoSetBajas">
      <wsdl:input message="tns:InfoSetBajasSoapIn" />
      <wsdl:output message="tns:InfoSetBajasSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="FileNetCodigoGrabar">
      <wsdl:input message="tns:FileNetCodigoGrabarSoapIn" />
      <wsdl:output message="tns:FileNetCodigoGrabarSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="BulkDetalle2">
      <wsdl:input message="tns:BulkDetalle2SoapIn" />
      <wsdl:output message="tns:BulkDetalle2SoapOut" />
    </wsdl:operation>
    <wsdl:operation name="BulkDetalle">
      <wsdl:input message="tns:BulkDetalleSoapIn" />
      <wsdl:output message="tns:BulkDetalleSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="CrearAltaDm">
      <wsdl:input message="tns:CrearAltaDmSoapIn" />
      <wsdl:output message="tns:CrearAltaDmSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="CrearBajaDm">
      <wsdl:input message="tns:CrearBajaDmSoapIn" />
      <wsdl:output message="tns:CrearBajaDmSoapOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="WsBulkSoap" type="tns:WsBulkSoap">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="Crear">
      <soap:operation soapAction="http://preingreso.metlife.cl/Crear" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CrearAlta">
      <soap:operation soapAction="http://preingreso.metlife.cl/CrearAlta" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CrearBaja">
      <soap:operation soapAction="http://preingreso.metlife.cl/CrearBaja" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Ejecutar">
      <soap:operation soapAction="http://preingreso.metlife.cl/Ejecutar" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Info">
      <soap:operation soapAction="http://preingreso.metlife.cl/Info" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ListarError">
      <soap:operation soapAction="http://preingreso.metlife.cl/ListarError" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ListarSet">
      <soap:operation soapAction="http://preingreso.metlife.cl/ListarSet" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="InfoSetBajas">
      <soap:operation soapAction="http://preingreso.metlife.cl/InfoSetBajas" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="FileNetCodigoGrabar">
      <soap:operation soapAction="http://preingreso.metlife.cl/FileNetCodigoGrabar" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BulkDetalle2">
      <soap:operation soapAction="http://preingreso.metlife.cl/BulkDetalle2" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BulkDetalle">
      <soap:operation soapAction="http://preingreso.metlife.cl/BulkDetalle" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CrearAltaDm">
      <soap:operation soapAction="http://preingreso.metlife.cl/CrearAltaDm" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CrearBajaDm">
      <soap:operation soapAction="http://preingreso.metlife.cl/CrearBajaDm" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="WsBulkSoap12" type="tns:WsBulkSoap">
    <soap12:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="Crear">
      <soap12:operation soapAction="http://preingreso.metlife.cl/Crear" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CrearAlta">
      <soap12:operation soapAction="http://preingreso.metlife.cl/CrearAlta" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CrearBaja">
      <soap12:operation soapAction="http://preingreso.metlife.cl/CrearBaja" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Ejecutar">
      <soap12:operation soapAction="http://preingreso.metlife.cl/Ejecutar" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Info">
      <soap12:operation soapAction="http://preingreso.metlife.cl/Info" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ListarError">
      <soap12:operation soapAction="http://preingreso.metlife.cl/ListarError" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ListarSet">
      <soap12:operation soapAction="http://preingreso.metlife.cl/ListarSet" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="InfoSetBajas">
      <soap12:operation soapAction="http://preingreso.metlife.cl/InfoSetBajas" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="FileNetCodigoGrabar">
      <soap12:operation soapAction="http://preingreso.metlife.cl/FileNetCodigoGrabar" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BulkDetalle2">
      <soap12:operation soapAction="http://preingreso.metlife.cl/BulkDetalle2" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BulkDetalle">
      <soap12:operation soapAction="http://preingreso.metlife.cl/BulkDetalle" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CrearAltaDm">
      <soap12:operation soapAction="http://preingreso.metlife.cl/CrearAltaDm" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CrearBajaDm">
      <soap12:operation soapAction="http://preingreso.metlife.cl/CrearBajaDm" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="WsBulk">
    <wsdl:port name="WsBulkSoap" binding="tns:WsBulkSoap">
      <soap:address location="http://10.77.208.109:8080/WsBulk.asmx" />
    </wsdl:port>
    <wsdl:port name="WsBulkSoap12" binding="tns:WsBulkSoap12">
      <soap12:address location="http://10.77.208.109:8080/WsBulk.asmx" />
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>