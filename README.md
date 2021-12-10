![](https://worldbankgroup-my.sharepoint.com/personal/ppopov_worldbankgroup_org/Documents/DOX/logo.png?Web=1) 
#<i>IVA Automation Project</i>

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger) 
![Editor](https://img.shields.io/github/release/pandao/editor.md.svg) 
[![License badge](https://img.shields.io/badge/license-Apache2-green.svg)](http://www.apache.org/licenses/LICENSE-2.0) 
![Maven Central](https://img.shields.io/maven-central/v/io.github.bonigarcia/selenium-jupiter.svg)
<br>

## <i>Introduction</i>


<p align="justify">
PLM QA C4E automation framework is an automation testing tool for SAP software systems. It helps to automate test  cases 
for  end  to  end  scenarios  in  SAP  systems. Tests are more robust during changes. Inspection process checks the data 
content with a component, and not just the screen object behavior. Functions that this solution performs are:
<br>[1] Streamlines the creation and maintenance of  ERP business processing testing. 
<br>[2] Performs end to end business process testing solution that accelerates testing of business processes.
<br>[3] Help  facilitate  testing  the  SAP  environment and to change the testing process trends from manual testing to 
testing by composition.
<br>[4] Helps to reduce the risk associated with business and support the entire SAP application. 
</p>


## <i>SAP GUI Common User Interface Elements</i>
<p>
<b>SAP Main Screen</b> 
<p>

![](https://dm2301files.storage.live.com/y4mUhfImGz9j1gfTfs9QPFMEu90Mmv_1t0XMWGZfp9B_PqUVRxzCuf8KU4c6Iwe6AArwFOu11LstawOxETlDbVUgnAMQW_uFQ6oXG1iqZTsqSsg8N_Avm5yGnqi0ThJOg1m0nyQky-PhRR0UWVKMjv6BIEvxm9z5oShEkvBbXR7NgBLexYwNo-WiKsNhAQ-sS72M-E5gfG9WSNLqzHh2NItcQ/AcroRd32_xmWj4gVnxO.png?psid=1&width=400&height=400)
<br> &nbsp; [A] Menu Bar
<br> &nbsp; [B] System Function Bar
<br> &nbsp; [C] Application Toolbar
<br> &nbsp; [D] Application Window
<br> &nbsp; [E] Status Bar
</p>
<br>

<p>

<b>A. Menu Bar</b><br>
![](https://dm2301files.storage.live.com/y4miNymMzFfWuCEGxRo908Q3AMKXE6_9j_a5uRDehL79mcWAd17q4FLfWbuKqF03iMvUg1XM3STgi_eXt_EQnnzTSTQKB-JrMg1rH4UVioly9ERkfTnJ7MYy9GbiOWqPLQ5NAaPsFOY-aOupj103nfZFw4E57_Rjue2k3hiy5g1ge_GY8fiZ9F9bH5gglfIgZEu9wzDxo-I4q1SBK8C6GYCbg/AcroRd32_1424KDJ3pa.png?psid=1&width=400&height=150)
<br>System &emsp;  - Contains functions, such as Create session, User profile, and Log off, that affect the system in general.
<br>Help &emsp; &emsp; - Can provide various forms of help. Atthe City, this Help menu is not connected to City Help.
<br>Edit &emsp; - 


![](https://dm2301files.storage.live.com/y4mpbpz3rchxAHyGqEegP9EswLeBSYk_qSrcQDehUVEvlBYypTiEBjj0te9-9IVAvb7ixnjA-pyWoKt_ZSg8KkdVqI7AtuGeenZ1IYejL2yYI-CtkGWyj7GchurVn7WWU23jktzL4_60pnf7qgLtQeFQmkQ6yZVn5XEF-k688XcjFOivDoLKJpbKq-mboivHo6v5lW6BmQiF699-SU81w56Vw/AcroRd32_Hyo404EVeL.png?psid=1&width=400&height=400)
</p>
<p align="justify", id="software">
<b>AutoIt</b><br>
AutoIt v3 is a freeware scripting language for  automating  the   Windows   GUI.
It uses a combination of simulated keystrokes, mouse movement and window/control 
manipulation in order to automate tasks.
</p>
<b>Jacob</b><br>
JACOB is a JAVA-COM Bridge that allows you to call COM Automation components  from 
Java. It uses JNI to make native calls to the COM libraries. JACOB runs on x86 and 
x64 environments <p id="sec">supporting</p> 32 bit and 64 bit JVMs.
</p>
<b>AutoItX4Java</b><br>
JACOB is a JAVA-COM Bridge that allows you to call COM Automation components  from 
Java. It uses JNI to make native calls to the COM libraries. JACOB runs on x86 and 
x64 environments supporting 32 bit and 64 bit JVMs.
</p>
<b>Winium.Desktop</b><br>
Winium.Desktop is an open source test automation tool  for  automated  testing  of 
Windows application based on WinForms and WPF platforms.
</p>

## <i>Installation Pack</i>
- [x] &nbsp; <a href = https://repo1.maven.org/maven2/xyz/cofe/jacob/1.20>JACOB Java COM Bridge</a>
- [x] &nbsp; <a href = https://github.com/2gis/Winium.Desktop/releases/>Winium Desktop</a>
- [x] &nbsp; <a href = https://www.autoitscript.com/cgi-bin/getfile.pl?autoit3/autoit-v3.zip>AutoIt</a>
- [x] &nbsp; <a href = https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/autoitx4java/AutoItX4Java.jar>AutoItXJava</a>

## <i>Setup Server Environment</i>

[ 1 ] &nbsp; <b>Download Installation Pack Items</b><br>

-[x] AutoIt v3
-[x] Jacob <b>dll</b> files
-[x] AutoItX4Java

<p>
[ 2 ] &nbsp; <b>Install AutoIt v3</b><br>
</p>
<p>
[ 3 ] &nbsp; <b>Register AutoIt COM libraries</b><br>
Open project <code>lib</code> folder and start command prompt as administrator. Then execute following commands:
<br><code>regvr32 AutoItX3.dll</code>
<br><code>regsvr32 AutoItX3_x64.dll</code>
</p>
<p>
[ 4 ] &nbsp; <b>JDK lib update</b><br>
Navigate to JDK installation folder and open <code>bin</code> folder. Then paste following files:

-[x] AutoItX4Java.jar
-[x] jacob.jar
-[x] jacob-1.20-x64.dll
-[x] jacob-1.20-x86.dll
</p>


### <i>Setup Local Machine</i>
<p>

</p>



### <i>Automation Arhitecture</i>
![Copy of Untitled Diagram](https://user-images.githubusercontent.com/48771359/120905594-c850e380-c65b-11eb-8c3e-225d3eb24e1d.png)

<br><br><br>
*****

### <i>Automation Framework</i>
- [x] Config       - <i>Responsible for loading the configurations from properties files, Initializing the 
                     WebDriver, Implicit Waits, Extent Reports and also to create the object of FileInputStream 
                     which is responsible for pointing towards the file from which the data should be read.</i><br><br>
- [x] Utilities    - <i>Stores and handles the functions (The code which is repetitive in nature such as waits, actions, capturing 
                     screenshots, accessing excels, sending email etc.,) which can be commonly used across the entire framework. The 
                     reason behind creating utility class is to achieve reusability.</i><br><br>
- [x] Test Data    - <i>Stores all StatusBarObjects variables that are used in tests. All the details which change as per the environment and 
                     authorization such as URL, Login Credentials are kept in separate classes. Keeping these details in a 
                     separate files makes easy to maintain.</i><br><br>
- [x] Test Scripts - <i>Creating StatusBarObjects cases takes less effort because StatusBarObjects scripts for different modules can be reused. There 
                     are two StatusBarObjects method types. Those two types are responsible for front-end and back-end tests.</i><br><br> 
- [x] Test Cases   - <i>These classes contains @Test annotations used for calling StatusBarObjects methods with different StatusBarObjects values.</i><br><br>
- [x] TestNG       - <i>XML files that representing StatusBarObjects suites that are created in StatusBarObjects cases classes.</i><br><br>
 

<br><br><br> 
*****

### <i>Advantages</i>
- [x] Execute StatusBarObjects suite that can be a combination between front-end and back-end StatusBarObjects scenarios as a single StatusBarObjects. 
      Framework structure supports full end to end testing (without performance and load). Extended framework 
      structure improves efficiency of every automated StatusBarObjects. 
- [x] Test coverage and high code re-usability are improved by separated StatusBarObjects methods. A single StatusBarObjects scenario can be 
      re executed with multiple StatusBarObjects data. This methodology reduces the number of StatusBarObjects code required to StatusBarObjects scenarios
      and data is easier to modify.  
- [x] Framework provides the fastest way to generate a StatusBarObjects suite. Test methods that calls different actions like
      <i>login, get products list, place order, etc.</i> provides structure that requires less coding as it covers 
      all the possible combination of StatusBarObjects scenarios.

<br><br><br>


#### <i>Build & Execute</i>

<p align="justify">
All in one means that the code for all tests is in one project. Combination between different tests and creating StatusBarObjects 
compilations is possible by creating a new XML files witch should contains wanted StatusBarObjects classes and StatusBarObjects methods. Those 
XML must be stored in <code>src/main/resource/xml_file</code>. Specific StatusBarObjects suite can be executed by changing the 
property value in <code>src/main/resource/config.properties</code>.
</p>

Run specific StatusBarObjects suite:
1. Create XML file that must contains specific StatusBarObjects methods.
2. Open _config.properties_ file and set the name of the XML file without any file extensions.
3. Run <code>/bin/build.sh</code> to compile your code.
4. Run <code>/bin/StatusBarObjects.sh</code>.


*****

### Automation Report

<p align="justify">
Most important part for every automation is the report. By using ExtentReport and Log4j libraries we have very detailed 
report that contains only important information. <a href = report/TestReport.html>TestReport.html</a> provides all of the 
necessary details for the bussiness and QA's. Configuration methods are in <code>ExtentManager.class</code>.  . Every API 
StatusBarObjects creates a file with JSON extension and store into it response from the server. 
This decision makes tests faster and separate reports for front-end from the back-end tests. More information about 
library can be found <a href = http://extentreports.com/docs/versions/3/java/#email-example>here</a>. Log events can be 
created by using <code>StatusBarObjects</code> keyword.
</p>

Example:
```
//*** Create log event ***//
StatusBarObjects.pass("Text");
``` 
</p>


#### <i>Report Functionality</i>
- [x]   REST/SOAP API request method, header and body details
        ([view](https://github.dxc.com/storage/user/52163/files/8a62a980-6386-11e9-9467-1561653f7117))
- [x]   REST/SOAP API response body, header and error messages details
        ([view](https://github.dxc.com/storage/user/52163/files/f9d99880-6388-11e9-89fd-c049052911ed))
- [x]   Stack trace and screenshots of the failed screens in case of exceptions
        ([view](https://github.dxc.com/storage/user/52163/files/4329e800-6389-11e9-8ac7-963abe443808))
- [x]   Views (
        [StatusBarObjects,](https://github.dxc.com/storage/user/52163/files/a588e580-6395-11e9-8b44-9c2adc9afc83)
        [category,](https://github.dxc.com/storage/user/52163/files/e54fcd00-6395-11e9-8ee6-1bea201c4e90)
        [exception,](https://github.dxc.com/storage/user/52163/files/f13b8f00-6395-11e9-9876-5b1a2aa4f549)
        [dashboard](https://github.dxc.com/storage/user/52163/files/fdbfe780-6395-11e9-853e-24f7e20b975f))
- [x]   Assign StatusBarObjects category and author
- [x]   Adding system info on the dashboard


*****


### [HttpClient](https://hc.apache.org/httpcomponents-client-4.5.x/tutorial/html/index.html)
<p align="justify">
Http client is a transfer library. It resides on the client side, sends and receives Http messages. 
It provides up to date, feature-rich, and an efficient implementation which meets the recent HTTP standards.
An HTTP client is one of the easiest clients to create. It’s very handy because it 
allows for the calling, not only of the internal methods as the native protocol does, 
but also of third- party calls implemented in plugins that can be only called via HTTP.
I have chosen the Apache HttpComponents that is one of the most widely used libraries 
for executing HTTP calls. This library is available in the main Maven repository search.maven.org. 
To enable the compilation in your Maven pom.xml project just add the following code:
<code>org.apache.httpcomponentshttpclient4.5.2</code>
</p>

#### <i>Features</i>
- [x] HttpClient library implements all the available HTTP methods.
- [x] HttpClient library provides APIs to secure the requests using the Secure Socket Layer protocol.
- [x] Using HttpClient, you can establish connections using proxies.
- [x] You can authenticate connections using authentication schemes such as Basic, Digest, NTLMv1, NTLMv2, NTLM2 Session etc.
- [x] HttpClient library supports sending requests through multiple threads. It manages multiple connections established 
      from various threads using ClientConnectionPoolManager.
- [x] Using Apache HttpClient library, you can set connection timeouts.


#### <i>Development Steps</i>

<p align="justify">
The first step is to initialize the HTTP client object. This is done via the following code:
<code>HttpClient client = HttpClientBuilder.create().build();</code>
</p>

<p align="justify">
Custom headers allow for passing extra information to the server for executing a call. Some 
examples could be API keys, or hints about supported formats. A typical example is using gzip 
data compression over HTTP to reduce bandwidth usage. To do that, we can add a custom header to 
the call informing the server that our client accepts encoding: Accept-Encoding, gzip:
<code>post.addHeader("Accept-Encoding", "gzip");</code>
</p>

<p style = "line-height: 1.1">
After configuring the call with all the parameters, we can fire up the request:
<code>HttpResponse response = client.execute(post);</code>
</p>

<p style = "line-height: 1.1">
We can read the answer from the server:
</p>

```
HttpEntity entity = response.getEntity();
String responseEntity = EntityUtils.toString(entity, "UTF-8");
```

<p style = "line-height: 1.1">

More documentation about
- [x] [Request Sending](https://hc.apache.org/httpcomponents-client-4.5.x/tutorial/html/fundamentals.html#d5e49)
- [x] [Response Receiving](https://hc.apache.org/httpcomponents-client-4.5.x/tutorial/html/fundamentals.html#d5e74)
- [x] [Entity](https://hc.apache.org/httpcomponents-client-4.5.x/tutorial/html/fundamentals.html#d5e95)
- [x] [Exception Handling](https://hc.apache.org/httpcomponents-client-4.5.x/tutorial/html/fundamentals.html#d5e279)
</p>

<br>


### [OkHttpClient](https://square.github.io/okhttp/3.x/okhttp/okhttp3/OkHttpClient.html)
<p align="justify">
After latest implementations the project supports both libraries. Using OkHttp is easy. Its request/response API is 
designed with fluent builders and immutability. It supports both synchronous blocking calls and async calls with callbacks. 
OkHttp performs best when you create a single OkHttpClient instance and reuse it for all of your HTTP calls. This is 
because each client holds its own connection pool and thread pools. Reusing connections and threads reduces latency and 
saves memory. Conversely, creating a client for each request wastes resources on idle pools.
</p>

OkHttp is an HTTP client that’s efficient by default:

- [x] HTTP/2 support allows all requests to the same host to share a socket.
- [x] Connection pooling reduces request latency (if HTTP/2 isn’t available).
- [x] Transparent GZIP shrinks download sizes.
- [x] Response caching avoids the network completely for repeat requests.
- [x] Supports Android 5.0+ (API level 21+) and Java 8+.

#### <i>How To Generate JSON</i>

<p align="justify">
My recommendation is to use GSON Java JSON API from Google and JSON.org API.
<a href = https://github.com/google/gson> [GSON] </a>  is reasonably flexible and easy to use. 
<a href = https://github.com/stleary/JSON-java> [JSON.org] </a> was one of the first Java JSON APIs available out there. 
It is reasonably easy to use, but not as flexible or fast as the other JSON APIs.
</p>

<br>
<br>

[ GSON ]
```jshelllanguage
Example_1
JSONObject jsonPostData = new JSONObject();
        jsonPostData.put("deviceToken", "07304e56c452be73ad2b51a4647d0300");
        jsonPostData.put("platform", "Android");
        jsonPostData.put("platformVersion", "6.0");
        jsonPostData.put("appId", 1);
        jsonPostData.put("frameworkVersion", "1.0.0");
        jsonPostData.put("model", "LG Nexus 5X");
        jsonPostData.put("appVersion", "1.0.0");
        
        
Result:
{
  "appVersion": "1.0.0",
  "frameworkVersion": "1.0.0",
  "platformVersion": "6.0",
  "appId": 1,
  "model": "LG Nexus 5X",
  "platform": "Android",
  "deviceToken": "07304e56c452be73ad2b51a4647d0300"
}




Example_2
JSONObject uuid = new JSONObject();
        uuid.put("uuid", itemID);

        JSONArray items = new JSONArray();
        items.put(uuid);

        JSONObject jsonPostData = new JSONObject();
        jsonPostData.put("locationId", id);
        jsonPostData.put("items", items);

                
Result:        
{
  "locationId": 141,
  "items": [
          {
              "uuid": "28436bdc-374d-5d61-94a7-039f01ec9615"
          }
     ]
}




Example_3
JSONObject dataset = new JSONObject();
        dataset.put("genre_id", 1);
        dataset.put("genre_parent_id", JSONObject.NULL);
        dataset.put("genre_title", "International");
        // use the accumulate function to add to an existing value. The value
        // will now be converted to a list
        dataset.accumulate("genre_title", "Pop");
        // append to the key
        dataset.append("genre_title", "slow");
        dataset.put("genre_handle", "International");
        dataset.put("genre_color", "#CC3300");
 
        // get the json array for a string
        System.out.println(dataset.getJSONArray("genre_title"));
        // prints ["International","Pop","slow"]
 
        // increment a number by 1
        dataset.increment("genre_id");
 
        // quote a string allowing the json to be delivered within html
        System.out.println(JSONObject.quote(dataset.toString()));
        // prints
        // "{\"genre_color\":\"#CC3300\",\"genre_title\":[\"International\",\"Pop\",\"slow\"],
        // \"genre_handle\":\"International\",\"genre_parent_id\":null,\"genre_id\":2}"
    }
 
}

Result:
"{\"genre_color\":\"#CC3300\",\"genre_title\":[\"International\",\"Pop\",\"slow\"],\"genre_handle\":\"International\",\"genre_parent_id\":null,\"genre_id\":2}"
```
</p>

*****


### Selenium
Environment allows to write automated tests that drive in a way similar to how a user would. At
this moment configuration supports parallel tests execution only on <i>Chrome</i> and <i>Firefox</i> browsers.
Browser configuration for now can is into `testng.xml` file.
<br>
Useful code snippets:
<br>

```
//*** Take screenshot ***//
takeScreenshot(driver, "FileName");
```

```
//*** Compare image from data base with actual screenshot ***//
long start = System.currentTimeMillis();
int q = 0;
File file1 = new File(filePath + "/" + "Screenshots/BufferedWriter_Article.txt");

FileWriter fw = new FileWriter(file1.getAbsoluteFile());
BufferedWriter bw = new BufferedWriter(fw);

// Image from data base
File fileA = new File(filePath + "/" + "Screenshots/Expected/Article_Expected.png");
BufferedImage image = ImageIO.read(fileA);
int width = image.getWidth(null);
int height = image.getHeight(null);
int[][] clr = new int[width][height];
    
// Actual screenshot
File fileB = new File(filePath + "/" + "Screenshots/Actual/Article_Actual.png");
BufferedImage images = ImageIO.read(fileB);
int widthe = images.getWidth(null);
int heighte = images.getHeight(null);
int[][] clre = new int[widthe][heighte];
int smw = 0;
int smh = 0;
int p = 0;

    if (width > widthe) {
        smw = widthe;
    } else {
        smw = width;
    }
    if (height > heighte) {
        smh = heighte;
    } else {
        smh = height;
    }

for (int a = 0; a < smw; a++) {
    for (int b = 0; b < smh; b++) {
    clre[a][b] = images.getRGB(a, b);
    clr[a][b] = image.getRGB(a, b);

if (clr[a][b] == clre[a][b]) {
    p = p + 1;
    bw.write("\t");
    bw.write(Integer.toString(a));
    bw.write("\t");
    bw.write(Integer.toString(b));
    bw.write("\n");
} else
    q = q + 1;
    }
}

float w, h = 0;
    if (width > widthe) {
        w = width;
    } else {
        w = widthe;
    }
    if (height > heighte) {
        h = height;
    } else {
        h = heighte;
    }
float s = (smw * smh);
float x = (100 * p) / s;

long stop = System.currentTimeMillis();

// Test will passed when percentage of sameness is more than 95%
    if (x > 95) {
        StatusBarObjects.pass(MarkupHelper.createLabel("Compare actual screenshot with screenshot from the data base", ExtentColor.GREEN));
        StatusBarObjects.pass("<pre>"
                    + "Success rate = " + x + "%" + "\n"
                    + "Time(ms) for visualization check = " + (stop - start) + "\n"
                    + "Number of pixels gets varied = " + q + "\n"
                    + "Number of pixels gets matched = " + p + "\n"
                    + "</pre>");
        StatusBarObjects.pass("[ ACTUAL PAGE VIEW ]", MediaEntityBuilder.createScreenCaptureFromPath("../Screenshots/Actual/Article_Actual.png").build());
        StatusBarObjects.pass("[ EXPECTED PAGE VIEW ]", MediaEntityBuilder.createScreenCaptureFromPath("../Screenshots/Expected/Article_Expected.png").build());
    }
        
// Test will generate warning when percentage of sameness is equal to 95%
    if (x == 95) {
        StatusBarObjects.warning(MarkupHelper.createLabel("Compare actual screenshot with screenshot from the data base", ExtentColor.ORANGE));
        StatusBarObjects.warning("<pre>"
                    + "Success rate = " + x + "%" + "\n"
                    + "Time(ms) for visualization check = " + (stop - start) + "\n"
                    + "Number of pixels gets varied = " + q + "\n"
                    + "Number of pixels gets matched = " + p + "\n"
                    + "</pre>");
        StatusBarObjects.warning("[ ACTUAL PAGE VIEW ]", MediaEntityBuilder.createScreenCaptureFromPath("../Screenshots/Actual/Article_Actual.png").build());
        StatusBarObjects.warning("[ EXPECTED PAGE VIEW ]", MediaEntityBuilder.createScreenCaptureFromPath("../Screenshots/Expected/Article_Expected.png").build());
    }
        
// Test will failed when percentage of sameness is less than 95%
    if (x < 95) {
        StatusBarObjects.fail(MarkupHelper.createLabel("Compare actual screenshot with screenshot from the data base has failed", ExtentColor.RED));
        StatusBarObjects.fail("<pre>"
                    + "Success rate = " + x + "%" + "\n"
                    + "Time(ms) for visualization check = " + (stop - start) + "\n"
                    + "Number of pixels gets varied = " + q + "\n"
                    + "Number of pixels gets matched = " + p + "\n"
                    + "</pre>");
        StatusBarObjects.fail("[ ACTUAL PAGE VIEW ]", MediaEntityBuilder.createScreenCaptureFromPath("../Screenshots/Actual/Article_Actual.png").build());
        StatusBarObjects.fail("[ EXPECTED PAGE VIEW ]", MediaEntityBuilder.createScreenCaptureFromPath("../Screenshots/Expected/Article_Expected.png").build());
```

<br>

### Build and Run

<br> [1] Open <i>config.properties</i> file and change the <i>xml</i> value with name of the xml file.
<br> [2] Run <i>build.sh</i> file that can be found in <i>bin</i> folder.
<br> [3] Run <i>StatusBarObjects.sh</i> file.
 
