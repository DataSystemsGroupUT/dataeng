package ee.ut.cs.dsg.dsg.udfs.udtf;

import io.confluent.ksql.function.udf.UdfParameter;
import io.confluent.ksql.function.udtf.Udtf;
import io.confluent.ksql.function.udtf.UdtfDescription;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@UdtfDescription(name = "split_string", description = "splits a string into words")
public class MySplit {
    final static Pattern pattern = Pattern.compile("\\W+", Pattern.UNICODE_CHARACTER_CLASS);
    @Udtf(description = "Splits a sentence into words")
    public List<String> split(@UdfParameter(value = "V1", description = "the first value") final String s) {
        String s1 = s.toLowerCase()
                .replace(",", " ")
                .replace(";", " ")
                .replace("/.", " ")
                .replace("!", " ")
                .replace("\"", " ")
                .replace("?", " ");
        return Arrays.asList(pattern.split(s1));
    }
}
