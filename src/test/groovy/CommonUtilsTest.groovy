import com.jmeter.utils.CommonUtils
import spock.lang.Specification

class CommonUtilsTest extends Specification {

    def "test log method"() {
        when:
        CommonUtils.log("Test log message")

        then:
        noExceptionThrown()
    }

    def "test set and get variable"() {
        given:
        String key = "testKey"
        String value = "testValue"

        when:
        CommonUtils.setVar(key, value)
        String result = CommonUtils.getVar(key)

        then:
        result == value
    }

    def "test sleep method"() {
        when:
        long start = System.currentTimeMillis()
        CommonUtils.sleep(100)
        long end = System.currentTimeMillis()

        then:
        (end - start) >= 100
    }

    def "test has variable"() {
        given:
        String key = "testKey"
        CommonUtils.setVar(key, "value")

        expect:
        CommonUtils.hasVar(key) == true
        CommonUtils.hasVar("nonExistentKey") == false
    }
} 