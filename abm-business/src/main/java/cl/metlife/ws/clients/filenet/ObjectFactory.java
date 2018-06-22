
package cl.metlife.ws.clients.filenet;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cl.blueprintsit.ws.filenet package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DeleteDocumentResponse_QNAME = new QName("http://filenet.ws.metlife.cl/", "deleteDocumentResponse");
    private final static QName _SearchDocumentVersions_QNAME = new QName("http://filenet.ws.metlife.cl/", "searchDocumentVersions");
    private final static QName _GetMetadata_QNAME = new QName("http://filenet.ws.metlife.cl/", "getMetadata");
    private final static QName _SearchDocuments_QNAME = new QName("http://filenet.ws.metlife.cl/", "searchDocuments");
    private final static QName _PutDocument_QNAME = new QName("http://filenet.ws.metlife.cl/", "putDocument");
    private final static QName _UpdateDocument_QNAME = new QName("http://filenet.ws.metlife.cl/", "updateDocument");
    private final static QName _IsConnected_QNAME = new QName("http://filenet.ws.metlife.cl/", "isConnected");
    private final static QName _PutDocumentResponse_QNAME = new QName("http://filenet.ws.metlife.cl/", "putDocumentResponse");
    private final static QName _SearchDocumentsResponse_QNAME = new QName("http://filenet.ws.metlife.cl/", "searchDocumentsResponse");
    private final static QName _UpdateDocumentResponse_QNAME = new QName("http://filenet.ws.metlife.cl/", "updateDocumentResponse");
    private final static QName _DeleteDocument_QNAME = new QName("http://filenet.ws.metlife.cl/", "deleteDocument");
    private final static QName _GetMetadataResponse_QNAME = new QName("http://filenet.ws.metlife.cl/", "getMetadataResponse");
    private final static QName _SearchDocumentVersionsResponse_QNAME = new QName("http://filenet.ws.metlife.cl/", "searchDocumentVersionsResponse");
    private final static QName _GetDocument_QNAME = new QName("http://filenet.ws.metlife.cl/", "getDocument");
    private final static QName _GetDocumentResponse_QNAME = new QName("http://filenet.ws.metlife.cl/", "getDocumentResponse");
    private final static QName _IsConnectedResponse_QNAME = new QName("http://filenet.ws.metlife.cl/", "isConnectedResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cl.blueprintsit.ws.filenet
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetDocumentResponse }
     * 
     */
    public GetDocumentResponse createGetDocumentResponse() {
        return new GetDocumentResponse();
    }

    /**
     * Create an instance of {@link GetDocument }
     * 
     */
    public GetDocument createGetDocument() {
        return new GetDocument();
    }

    /**
     * Create an instance of {@link Metadata }
     * 
     */
    public Metadata createMetadata() {
        return new Metadata();
    }

    /**
     * Create an instance of {@link UpdateDocument }
     * 
     */
    public UpdateDocument createUpdateDocument() {
        return new UpdateDocument();
    }

    /**
     * Create an instance of {@link UpdateDocumentResponse }
     * 
     */
    public UpdateDocumentResponse createUpdateDocumentResponse() {
        return new UpdateDocumentResponse();
    }

    /**
     * Create an instance of {@link Document }
     * 
     */
    public Document createDocument() {
        return new Document();
    }

    /**
     * Create an instance of {@link GetMetadata }
     * 
     */
    public GetMetadata createGetMetadata() {
        return new GetMetadata();
    }

    /**
     * Create an instance of {@link SearchDocumentsResponse }
     * 
     */
    public SearchDocumentsResponse createSearchDocumentsResponse() {
        return new SearchDocumentsResponse();
    }

    /**
     * Create an instance of {@link SearchDocuments }
     * 
     */
    public SearchDocuments createSearchDocuments() {
        return new SearchDocuments();
    }

    /**
     * Create an instance of {@link GetMetadataResponse }
     * 
     */
    public GetMetadataResponse createGetMetadataResponse() {
        return new GetMetadataResponse();
    }

    /**
     * Create an instance of {@link SearchDocumentVersions }
     * 
     */
    public SearchDocumentVersions createSearchDocumentVersions() {
        return new SearchDocumentVersions();
    }

    /**
     * Create an instance of {@link IsConnectedResponse }
     * 
     */
    public IsConnectedResponse createIsConnectedResponse() {
        return new IsConnectedResponse();
    }

    /**
     * Create an instance of {@link PutDocumentResponse }
     * 
     */
    public PutDocumentResponse createPutDocumentResponse() {
        return new PutDocumentResponse();
    }

    /**
     * Create an instance of {@link DeleteDocumentResponse }
     * 
     */
    public DeleteDocumentResponse createDeleteDocumentResponse() {
        return new DeleteDocumentResponse();
    }

    /**
     * Create an instance of {@link IsConnected }
     * 
     */
    public IsConnected createIsConnected() {
        return new IsConnected();
    }

    /**
     * Create an instance of {@link SearchDocumentVersionsResponse }
     * 
     */
    public SearchDocumentVersionsResponse createSearchDocumentVersionsResponse() {
        return new SearchDocumentVersionsResponse();
    }

    /**
     * Create an instance of {@link PutDocument }
     * 
     */
    public PutDocument createPutDocument() {
        return new PutDocument();
    }

    /**
     * Create an instance of {@link DeleteDocument }
     * 
     */
    public DeleteDocument createDeleteDocument() {
        return new DeleteDocument();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteDocumentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://filenet.ws.metlife.cl/", name = "deleteDocumentResponse")
    public JAXBElement<DeleteDocumentResponse> createDeleteDocumentResponse(DeleteDocumentResponse value) {
        return new JAXBElement<DeleteDocumentResponse>(_DeleteDocumentResponse_QNAME, DeleteDocumentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchDocumentVersions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://filenet.ws.metlife.cl/", name = "searchDocumentVersions")
    public JAXBElement<SearchDocumentVersions> createSearchDocumentVersions(SearchDocumentVersions value) {
        return new JAXBElement<SearchDocumentVersions>(_SearchDocumentVersions_QNAME, SearchDocumentVersions.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMetadata }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://filenet.ws.metlife.cl/", name = "getMetadata")
    public JAXBElement<GetMetadata> createGetMetadata(GetMetadata value) {
        return new JAXBElement<GetMetadata>(_GetMetadata_QNAME, GetMetadata.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchDocuments }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://filenet.ws.metlife.cl/", name = "searchDocuments")
    public JAXBElement<SearchDocuments> createSearchDocuments(SearchDocuments value) {
        return new JAXBElement<SearchDocuments>(_SearchDocuments_QNAME, SearchDocuments.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PutDocument }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://filenet.ws.metlife.cl/", name = "putDocument")
    public JAXBElement<PutDocument> createPutDocument(PutDocument value) {
        return new JAXBElement<PutDocument>(_PutDocument_QNAME, PutDocument.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateDocument }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://filenet.ws.metlife.cl/", name = "updateDocument")
    public JAXBElement<UpdateDocument> createUpdateDocument(UpdateDocument value) {
        return new JAXBElement<UpdateDocument>(_UpdateDocument_QNAME, UpdateDocument.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsConnected }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://filenet.ws.metlife.cl/", name = "isConnected")
    public JAXBElement<IsConnected> createIsConnected(IsConnected value) {
        return new JAXBElement<IsConnected>(_IsConnected_QNAME, IsConnected.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PutDocumentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://filenet.ws.metlife.cl/", name = "putDocumentResponse")
    public JAXBElement<PutDocumentResponse> createPutDocumentResponse(PutDocumentResponse value) {
        return new JAXBElement<PutDocumentResponse>(_PutDocumentResponse_QNAME, PutDocumentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchDocumentsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://filenet.ws.metlife.cl/", name = "searchDocumentsResponse")
    public JAXBElement<SearchDocumentsResponse> createSearchDocumentsResponse(SearchDocumentsResponse value) {
        return new JAXBElement<SearchDocumentsResponse>(_SearchDocumentsResponse_QNAME, SearchDocumentsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateDocumentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://filenet.ws.metlife.cl/", name = "updateDocumentResponse")
    public JAXBElement<UpdateDocumentResponse> createUpdateDocumentResponse(UpdateDocumentResponse value) {
        return new JAXBElement<UpdateDocumentResponse>(_UpdateDocumentResponse_QNAME, UpdateDocumentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteDocument }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://filenet.ws.metlife.cl/", name = "deleteDocument")
    public JAXBElement<DeleteDocument> createDeleteDocument(DeleteDocument value) {
        return new JAXBElement<DeleteDocument>(_DeleteDocument_QNAME, DeleteDocument.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMetadataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://filenet.ws.metlife.cl/", name = "getMetadataResponse")
    public JAXBElement<GetMetadataResponse> createGetMetadataResponse(GetMetadataResponse value) {
        return new JAXBElement<GetMetadataResponse>(_GetMetadataResponse_QNAME, GetMetadataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchDocumentVersionsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://filenet.ws.metlife.cl/", name = "searchDocumentVersionsResponse")
    public JAXBElement<SearchDocumentVersionsResponse> createSearchDocumentVersionsResponse(SearchDocumentVersionsResponse value) {
        return new JAXBElement<SearchDocumentVersionsResponse>(_SearchDocumentVersionsResponse_QNAME, SearchDocumentVersionsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDocument }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://filenet.ws.metlife.cl/", name = "getDocument")
    public JAXBElement<GetDocument> createGetDocument(GetDocument value) {
        return new JAXBElement<GetDocument>(_GetDocument_QNAME, GetDocument.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDocumentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://filenet.ws.metlife.cl/", name = "getDocumentResponse")
    public JAXBElement<GetDocumentResponse> createGetDocumentResponse(GetDocumentResponse value) {
        return new JAXBElement<GetDocumentResponse>(_GetDocumentResponse_QNAME, GetDocumentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsConnectedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://filenet.ws.metlife.cl/", name = "isConnectedResponse")
    public JAXBElement<IsConnectedResponse> createIsConnectedResponse(IsConnectedResponse value) {
        return new JAXBElement<IsConnectedResponse>(_IsConnectedResponse_QNAME, IsConnectedResponse.class, null, value);
    }

}
