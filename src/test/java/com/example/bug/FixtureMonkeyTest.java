package com.example.bug;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.generator.ArbitraryContainerInfo;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.api.plugin.InterfacePlugin;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.codec.multipart.FilePart;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class FixtureMonkeyTest {

    private final static InterfacePlugin interfacePlugin = new InterfacePlugin();

    static {
        interfacePlugin.interfaceImplements(Book.class, List.of(FantasyBook.class));
        interfacePlugin.abstractClassExtends(EntityAttribute.class, List.of(NumberEntityAttribute.class));
        interfacePlugin.abstractClassExtends(Condition.class, List.of(ValueCondition.class, ListValueCondition.class));
        interfacePlugin.abstractClassExtends(Letter.class, List.of(A.class, B.class));
        interfacePlugin.abstractClassExtends(Value.class, List.of(StringValue.class));
        interfacePlugin.abstractClassExtends(EvaluationValue.class,
                List.of(StringEvaluationValue.class, ListEvaluationValue.class, AttributeEvaluationValue.class));
    }

    private final static FixtureMonkey FIXTURE_MONKEY = FixtureMonkey.builder()
            .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
            .defaultArbitraryContainerInfoGenerator(context -> new ArbitraryContainerInfo(1, 1))
            .nullableContainer(false)
            .defaultNotNull(true)
            .nullableElement(false)
            .plugin(interfacePlugin)
            .pushAssignableTypeArbitraryIntrospector(Record.class, ConstructorPropertiesArbitraryIntrospector.INSTANCE)
            .pushAssignableTypeArbitraryIntrospector(Timestamp.class, ConstructorPropertiesArbitraryIntrospector.INSTANCE)
            .pushAssignableTypeArbitraryIntrospector(URL.class, ConstructorPropertiesArbitraryIntrospector.INSTANCE)
            .build();

    @RepeatedTest(100)
    @Disabled
    void bug() {
        List<Node> nodes = FIXTURE_MONKEY.giveMe(Node.class, 25);

        List<String> names = nodes.stream()
                .map(node -> node.name)
                .toList();

        Map<String, Long> duplicateCount = names.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        List<String> duplicateStrings = duplicateCount.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println("Duplicate strings: " + duplicateStrings);


        assertThat(duplicateStrings).isEmpty();
    }

    @Test
    void anotherBug() {
        View view = FIXTURE_MONKEY.giveMeBuilder(new View())
                .set("version", new AggregateVersion(1))
                .set("deleted", true)
                .sample();

        assertThat(view.isDeleted()).isTrue();
        assertThat(view.getVersion().getValue()).isNotEqualTo(0);
    }

    @Test
    void filePart() {
        FilePart filePart = FIXTURE_MONKEY.giveMeOne(FilePart.class);

        assertThat(filePart).isNotNull();
    }

    @Test
    void atDataBug() {
        DataTest dataTest = FIXTURE_MONKEY.giveMeOne(DataTest.class);

        assertThat(dataTest).isNotNull();
        assertThat(dataTest.getId()).isNotNull();
        assertThat(dataTest.getName()).isNotNull();
    }

    @Test
    void nodeBug() {
        Node node = FIXTURE_MONKEY.giveMeOne(Node.class);

        assertThat(node.nodes).isNotEmpty();

        Node parentNode = FIXTURE_MONKEY.giveMeBuilder(Node.class)
                .set("nodes", List.of(node))
                .sample();

        assertThat(parentNode).isNotNull();
        assertThat(parentNode.nodes).isNotEmpty();
        assertThat(parentNode.nodes.get(0).nodes).isNotEmpty();
    }

    @RepeatedTest(100)
    void evaluationValue() {
        List<EvaluationValue> stringEvaluationValues =
                FIXTURE_MONKEY.giveMe(StringEvaluationValue.class, 2)
                        .stream()
                        .map(EvaluationValue.class::cast)
                        .toList();

        EvaluationValue listEvaluationValue = new ListEvaluationValue(
               stringEvaluationValues
        );

        AttributeEvaluationValue attributeEvaluationValue = FIXTURE_MONKEY.giveMeBuilder(AttributeEvaluationValue.class)
                .set("value", listEvaluationValue)
                .sample();

        assertThat(attributeEvaluationValue).isNotNull();
        assertThat(attributeEvaluationValue.value()).isEqualTo(listEvaluationValue);
    }

    @Test
    void timestamp() {
        Timestamp timestamp = FIXTURE_MONKEY.giveMeOne(Timestamp.class);

        assertThat(timestamp).isNotNull();
    }

    @Test
    void recordBug() {
        WorkflowRecord workflowRecord = FIXTURE_MONKEY.giveMeOne(WorkflowRecord.class);

        assertThat(workflowRecord).isNotNull();
    }

    @Test
    void locale() {
        URL locale = FIXTURE_MONKEY.giveMeOne(URL.class);

        assertThat(locale).isNotNull();
    }

    @Test
    void objectTest() {
        ObjectTest sample = FIXTURE_MONKEY.giveMeBuilder(ObjectTest.class)
                .set("object", "test")
                .sample();

        assertThat(sample.getObject()).isEqualTo("test");
    }

    @Test
    void setBug() {
        NumberEntityAttribute entityAttribute1 = FIXTURE_MONKEY.giveMeOne(NumberEntityAttribute.class);
        List<UUID> before = new ArrayList<>(entityAttribute1.getPermissions()
                .stream()
                .map(EntityAttributePermission::getEntityAttributePermissionId)
                .toList());

        FIXTURE_MONKEY.giveMeOne(NumberEntityAttribute.class);
        List<UUID> after = new ArrayList<>(entityAttribute1.getPermissions()
                .stream()
                .map(EntityAttributePermission::getEntityAttributePermissionId)
                .toList());

        assertThat(before).isEqualTo(after);
    }

    @Test
    void constructor() {
        FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
                .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
                .defaultArbitraryContainerInfoGenerator(context -> new ArbitraryContainerInfo(1, 1))
                .nullableContainer(false)
                .defaultNotNull(true)
                .nullableElement(false)
                .plugin(interfacePlugin)
                .pushAssignableTypeArbitraryIntrospector(Value.class, ConstructorPropertiesArbitraryIntrospector.INSTANCE)
                .build();

        Value value = fixtureMonkey.giveMeOne(Value.class);

        assertThat(value).isNotNull();
    }

    @RepeatedTest(100)
    void letterTest() {
        B b = FIXTURE_MONKEY.giveMeOne(B.class);

        Alphabet alphabet1 = FIXTURE_MONKEY.giveMeBuilder(Alphabet.class)
                .set("letters", List.of(b))
                .sample();

        assertThat(alphabet1.getLetters()).singleElement().isExactlyInstanceOf(B.class);
    }
}
