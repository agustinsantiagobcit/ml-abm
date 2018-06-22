<?xml version="1.0" encoding="utf-8"?>
<wsdl:definitions xmlns:tm="http://microsoft.com/wsdl/mime/textMatching/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:tns="http://preingreso.metlife.cl/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:s="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" targetNamespace="http://preingreso.metlife.cl/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <wsdl:types>
    <s:schema elementFormDefault="qualified" targetNamespace="http://preingreso.metlife.cl/">
      <s:element name="Logon">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="login" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="pwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="LogonResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="LogonResult" type="tns:SesionBo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="SesionBo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="Id" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="Activo" type="s:boolean" />
          <s:element minOccurs="0" maxOccurs="1" name="Error" type="tns:ErrorBo" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ErrorBo">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="Numero" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="Descripcion" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="Logout">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="sesionId" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="LogoutResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="LogoutResult" type="tns:ErrorBo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="EvaluarSesion">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="sesionId" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="EvaluarSesionResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="EvaluarSesionResult" type="tns:SesionBo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Log">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="sesionId" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="login" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="fechaDesde" nillable="true" type="s:dateTime" />
            <s:element minOccurs="1" maxOccurs="1" name="fechaHasta" nillable="true" type="s:dateTime" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="LogResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="LogResult" type="tns:LogBo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="LogBo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="Transacciones" type="tns:ArrayOfTransaccionBo" />
          <s:element minOccurs="0" maxOccurs="1" name="Error" type="tns:ErrorBo" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfTransaccionBo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="TransaccionBo" nillable="true" type="tns:TransaccionBo" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="TransaccionBo">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="Fecha" nillable="true" type="s:dateTime" />
          <s:element minOccurs="0" maxOccurs="1" name="Evento" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Login" type="s:string" />
        </s:sequence>
      </s:complexType>
    </s:schema>
  </wsdl:types>
  <wsdl:message name="LogonSoapIn">
    <wsdl:part name="parameters" element="tns:Logon" />
  </wsdl:message>
  <wsdl:message name="LogonSoapOut">
    <wsdl:part name="parameters" element="tns:LogonResponse" />
  </wsdl:message>
  <wsdl:message name="LogoutSoapIn">
    <wsdl:part name="parameters" element="tns:Logout" />
  </wsdl:message>
  <wsdl:message name="LogoutSoapOut">
    <wsdl:part name="parameters" element="tns:LogoutResponse" />
  </wsdl:message>
  <wsdl:message name="EvaluarSesionSoapIn">
    <wsdl:part name="parameters" element="tns:EvaluarSesion" />
  </wsdl:message>
  <wsdl:message name="EvaluarSesionSoapOut">
    <wsdl:part name="parameters" element="tns:EvaluarSesionResponse" />
  </wsdl:message>
  <wsdl:message name="LogSoapIn">
    <wsdl:part name="parameters" element="tns:Log" />
  </wsdl:message>
  <wsdl:message name="LogSoapOut">
    <wsdl:part name="parameters" element="tns:LogResponse" />
  </wsdl:message>
  <wsdl:portType name="WsSesionSoap">
    <wsdl:operation name="Logon">
      <wsdl:input message="tns:LogonSoapIn" />
      <wsdl:output message="tns:LogonSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Logout">
      <wsdl:input message="tns:LogoutSoapIn" />
      <wsdl:output message="tns:LogoutSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="EvaluarSesion">
      <wsdl:input message="tns:EvaluarSesionSoapIn" />
      <wsdl:output message="tns:EvaluarSesionSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Log">
      <wsdl:input message="tns:LogSoapIn" />
      <wsdl:output message="tns:LogSoapOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="WsSesionSoap" type="tns:WsSesionSoap">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="Logon">
      <soap:operation soapAction="http://preingreso.metlife.cl/Logon" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Logout">
      <soap:operation soapAction="http://preingreso.metlife.cl/Logout" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="EvaluarSesion">
      <soap:operation soapAction="http://preingreso.metlife.cl/EvaluarSesion" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Log">
      <soap:operation soapAction="http://preingreso.metlife.cl/Log" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="WsSesionSoap12" type="tns:WsSesionSoap">
    <soap12:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="Logon">
      <soap12:operation soapAction="http://preingreso.metlife.cl/Logon" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Logout">
      <soap12:operation soapAction="http://preingreso.metlife.cl/Logout" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="EvaluarSesion">
      <soap12:operation soapAction="http://preingreso.metlife.cl/EvaluarSesion" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Log">
      <soap12:operation soapAction="http://preingreso.metlife.cl/Log" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="WsSesion">
    <wsdl:port name="WsSesionSoap" binding="tns:WsSesionSoap">
      <soap:address location="http://10.77.48.122:8080/WsSesion.asmx" />
    </wsdl:port>
    <wsdl:port name="WsSesionSoap12" binding="tns:WsSesionSoap12">
      <soap12:address location="http://10.77.48.122:8080/WsSesion.asmx" />
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>