import com.jmeter.utils.AssertUtils
import spock.lang.Specification

class AssertUtilsTest extends Specification {

    def "test assertContains"() {
        when:
        AssertUtils.assertContains("This is a test response", "test")

        then:
        noExceptionThrown()
    }

    def "test assertEquals"() {
        when:
        AssertUtils.assertEquals(200, 200)

        then:
        noExceptionThrown()
    }

    def "test assertNotNull"() {
        when:
        AssertUtils.assertNotNull("Not Null")

        then:
        noExceptionThrown()
    }

    def "test assertStatusCode"() {
        when:
        AssertUtils.assertStatusCode(200, 200)

        then:
        noExceptionThrown()
    }

    def "test assertResponseContains"() {
        when:
        AssertUtils.assertResponseContains("Response with field", "field")

        then:
        noExceptionThrown()
    }

    def "test assertResponseTime"() {
        when:
        AssertUtils.assertResponseTime(1000, 500)

        then:
        noExceptionThrown()
    }
} 