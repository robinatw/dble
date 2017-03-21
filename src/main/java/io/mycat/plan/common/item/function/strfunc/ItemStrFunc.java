package io.mycat.plan.common.item.function.strfunc;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import io.mycat.plan.common.item.Item;
import io.mycat.plan.common.item.function.ItemFunc;
import io.mycat.plan.common.time.MySQLTime;


public abstract class ItemStrFunc extends ItemFunc {

	protected static final String EMPTY = new String("");

	public ItemStrFunc() {
		this(new ArrayList<Item>());
	}

	public ItemStrFunc(Item a) {
		this(new ArrayList<Item>());
		args.add(a);
	}

	public ItemStrFunc(Item a, Item b) {
		this(new ArrayList<Item>());
		args.add(a);
		args.add(b);
	}

	public ItemStrFunc(List<Item> args) {
		super(args);
		decimals = NOT_FIXED_DEC;
	}

	@Override
	public ItemResult resultType() {
		return ItemResult.STRING_RESULT;
	}

	@Override
	public void fixLengthAndDec() {

	}

	@Override
	public BigDecimal valReal() {
		String res = valStr();
		if (res == null)
			return BigDecimal.ZERO;
		try {
			return new BigDecimal(res);
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}

	@Override
	public BigInteger valInt() {
		String res = valStr();
		if (res == null)
			return BigInteger.ZERO;
		try {
			return new BigInteger(res);
		} catch (Exception e) {
			return BigInteger.ZERO;
		}
	}

	@Override
	public BigDecimal valDecimal() {
		String res = valStr();
		if (res == null)
			return null;
		try {
			return new BigDecimal(res);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean getDate(MySQLTime ltime, long fuzzydate) {
		return getDateFromString(ltime, fuzzydate);
	}

	@Override
	public boolean getTime(MySQLTime ltime) {
		return getTimeFromString(ltime);
	}

}