public class AuditEventNotifier extends EventNotifierSupport {

    public void notify(EventObject event) throws Exception {
        if (event instanceof ExchangeSentEvent) {
            ExchangeSentEvent sent = (ExchangeSentEvent) event;
            log.info(">>> Took " + sent.getTimeTaken() + " millis to send to external system : " + sent.getEndpoint());
        }

        if (event instanceof ExchangeCompletedEvent) {
            ExchangeCompletedEvent exchangeCompletedEvent = (ExchangeCompletedEvent) event;
            Exchange exchange = exchangeCompletedEvent.getExchange();
            String routeId = exchange.getFromRouteId();
            Date created = ((ExchangeCompletedEvent) event).getExchange().getProperty(Exchange.CREATED_TIMESTAMP, Date.class);
            // calculate elapsed time
            Date now = new Date();
            long elapsed = now.getTime() - created.getTime();
            log.info(">>> Took " + elapsed + " millis for the exchange on the route : " + routeId);
        }
    }

    public boolean isEnabled(EventObject event) {
        return true;
    }

    protected void doStart() throws Exception {

        // filter out unwanted events
        setIgnoreCamelContextEvents(true);
        setIgnoreServiceEvents(true);
        setIgnoreRouteEvents(true);
        setIgnoreExchangeCreatedEvent(true);
        setIgnoreExchangeCompletedEvent(false);
        setIgnoreExchangeFailedEvents(true);
        setIgnoreExchangeRedeliveryEvents(true);
        setIgnoreExchangeSentEvents(false);
    }

    protected void doStop() throws Exception {
        // noop
    }
}

@FixedLengthRecord(length = 60, paddingChar = ' ')
public static class Order {

    @DataField(pos = 1, length = 2) private int orderNr;
    @DataField(pos = 3, length = 2) private String clientNr;
    @DataField(pos = 5, length = 9) private String firstName;
    @DataField(pos = 14, length = 5, align = "L") private String lastName;
    @DataField(pos = 19, length = 4) private String instrumentCode;
    @DataField(pos = 23, length = 10) private String instrumentNumber;
    @DataField(pos = 33, length = 3) private String orderType;
    @DataField(pos = 36, length = 5) private String instrumentType;
    @DataField(pos = 41, precision = 2, length = 7) private BigDecimal amount;
    @DataField(pos = 48, length = 3) private String currency;
    @DataField(pos = 51, length = 10, pattern = "dd-MM-yyyy") private Date orderDate;