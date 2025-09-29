public class App {
    public static final String FRUITS_CSV    = "fruits.csv";

    public static void main(String[] args) throws Exception {
        ITokenizer tokenizer = new RegexTokenizer("[ \\t\\n'\",.?!]+");
        ITextNormalizer normalizer = new LowercaseTrimNormalizer();
        CountableWordRepository store = new CountableWordRepository();
        CountableWordProcessor service = new CountableWordProcessor(tokenizer, normalizer, store);
        ConsolePrinter printer = new ConsolePrinter();

        service.process(FRUITS_CSV);
        System.out.println("== 초기 ==");
        printer.print(service.viewByInitial());

        // remove banana
        service.removeOne("banana");
        System.out.println("== banana 1회 제거 후 ==");
        printer.print(service.viewByInitial());

        // remove banana
        service.removeOne("banana");
        System.out.println("== banana 2회 제거 후 ==");
        printer.print(service.viewByInitial());

        // add date
        service.addOne("date");
        System.out.println("== date 추가 후 ==");
        printer.print(service.viewByInitial());
    }
}
