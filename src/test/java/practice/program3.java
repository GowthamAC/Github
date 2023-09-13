package practice;

public class program3 {
public static void main(String[] args) {
	WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/challenging_dom");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void fetchData() {
        ArrayList<Map<String, String>> list = null;
        Map<String, String> map = null;
        List<WebElement> headers = driver.findElements(By.xpath("//table//thead//th"));
        List<WebElement> rows = driver.findElements(By.xpath("//table//tbody//tr"));
        List<WebElement> columns = driver.findElements(By.xpath("//table//tbody//tr[" + 1 + "]//td"));
        list = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            map = new LinkedHashMap<>();
            for (int j = 1; j <= columns.size(); j++) {
                String key = headers.get(j - 1).getText();
                String value = driver.findElement(By.xpath("//table//tbody//tr[" + (i + 1) + "]//td[" + j + "]")).getText();
                map.put(key, value);
            }
            list.add(map);
        }
        //to directly print the list of maps
        list.forEach(System.out::println);

        //to print in the form of key and value pairs
        list.forEach(m -> {
            m.forEach((key, value) -> {
                System.out.println(key + ": " + value);
            });
            System.out.println();
        });
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
}
}
