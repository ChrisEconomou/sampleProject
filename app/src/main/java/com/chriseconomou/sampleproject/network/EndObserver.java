package com.chriseconomou.sampleproject.network;

import rx.Observer;

public abstract class EndObserver<T> implements Observer<T> {

	@Override
    public void onCompleted() {
	}

	@Override
	public void onError(Throwable throwable) {
	}

	@Override
	public void onNext(T t) {
	}

}