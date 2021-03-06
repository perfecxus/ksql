/*
 * Copyright 2019 Confluent Inc.
 *
 * Licensed under the Confluent Community License; you may not use this file
 * except in compliance with the License.  You may obtain a copy of the License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.ksql.execution.plan;

import com.google.common.testing.EqualsTester;
import io.confluent.ksql.execution.expression.tree.Expression;
import io.confluent.ksql.name.ColumnName;
import java.util.Optional;
import org.apache.kafka.connect.data.Struct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StreamSelectKeyTest {
  @Mock
  private ExecutionStepPropertiesV1 properties1;
  @Mock
  private ExecutionStepPropertiesV1 properties2;
  @Mock
  private ExecutionStep<KStreamHolder<Struct>> source1;
  @Mock
  private ExecutionStep<KStreamHolder<Struct>> source2;
  @Mock
  private Expression expression1;
  @Mock
  private Expression expression2;
  @Mock
  private ColumnName alias;

  @SuppressWarnings("UnstableApiUsage")
  @Test
  public void shouldImplementEquals() {
    new EqualsTester()
        .addEqualityGroup(
            new StreamSelectKey(properties1, source1, expression1, Optional.of(alias)),
            new StreamSelectKey(properties1, source1, expression1, Optional.of(alias)))
        .addEqualityGroup(new StreamSelectKey(properties2, source1, expression1, Optional.of(alias)))
        .addEqualityGroup(new StreamSelectKey(properties1, source2, expression1, Optional.of(alias)))
        .addEqualityGroup(new StreamSelectKey(properties1, source1, expression2, Optional.of(alias)))
        .addEqualityGroup(new StreamSelectKey(properties1, source1, expression1, Optional.empty()));
  }
}