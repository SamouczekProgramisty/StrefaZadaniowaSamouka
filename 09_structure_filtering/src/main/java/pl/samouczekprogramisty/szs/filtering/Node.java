package pl.samouczekprogramisty.szs.filtering;

import java.util.Objects;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Node implements INode {
    @Getter private final String code;
    @Getter private final String renderer;

    @Override
    public Stream<INode> toStream() {
        return Stream.of(this);
    }

	@Override
	public boolean isUnique(INode node) {
		boolean hasDifferentCode = !node.getCode().equals(code);
		boolean hasDifferentRender = !node.getRenderer().equals(renderer);
		return hasDifferentCode || hasDifferentRender;
	}
}
