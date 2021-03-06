package br.ufsc.inf.ine5611.converters.scheduled.impl;

import br.ufsc.inf.ine5611.converters.Converter;
import br.ufsc.inf.ine5611.converters.scheduled.ConverterTask;
import br.ufsc.inf.ine5611.converters.scheduled.Priority;
import br.ufsc.inf.ine5611.converters.scheduled.ScheduledConverter;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class PriorityScheduledConverter implements ScheduledConverter {
    public static final int DEFAULT_QUANTUM_LOW = 50;
    public static final int DEFAULT_QUANTUM_NORMAL = 100;
    public static final int DEFAULT_QUANTUM_HIGH = 200;

    public PriorityScheduledConverter(Converter converter) {
        //TODO implementar
        /* - Salve converter como um field, para uso posterior
           - Registre um listener em converter.addCompletionListener() para que você saiba
         *   quando uma tarefa terminou */
    }

    @Override
    public void setQuantum(Priority priority, int milliseconds) {
        /* Dica: use um HasMap<Priority, Integer> para manter os quanta configurados para
         * cada prioridade */
        //TODO implementar
    }

    @Override
    public int getQuantum(Priority priority) {
        /* Veja setQuantum */
        //TODO implementar
        return 0;
    }

    @Override
    public Collection<ConverterTask> getAllTasks() {
        /* Junte todas as tarefas não completas em um Collection */
        //TODO implementar
        return null;
    }

    @Override
    public synchronized ConverterTask convert(InputStream inputStream, OutputStream outputStream,
                                String mediaType, long inputBytes, Priority priority) {
    	Consumer<ConverterTask> cancelCallback = (x) -> {		
    	};
    	Consumer<String> c = (x) -> System.out.println(x.toLowerCase());
    	long epoch = 1;
    	ScheduledConverterTask tarefa1 = new ScheduledConverterTask(inputStream, outputStream, mediaType, cancelCallback, inputBytes, Priority.HIGH, epoch);
    	ScheduledConverterTask tarefa2 = new ScheduledConverterTask(inputStream, outputStream, mediaType, cancelCallback, inputBytes, Priority.LOW, epoch);
    	
    	PriorityQueue<Consumer> queue = new PriorityQueue<Consumer>() {
    		public int compare(ScheduledConverterTask t1, ScheduledConverterTask t2){
    			int diff = Integer.compare(getPriorityAsInteger(t1.getPriority()), getPriorityAsInteger(t2.getPriority())); //<0 se l>r
				return 0;
    			
    		}
    	};
    	
        /* - Crie um objeto ScheduledConverterTask utilizando os parâmetros dessa chamada
         * - Adicione o objeto em alguma fila (é possível implementar com uma ou várias filas)
         * - Se a nova tarefa for mais prioritária que a atualmente executando, interrompa */
        //TODO implementar
        return null;
    }
    
    private int getPriorityAsInteger(Priority p){
    	switch (p){
    	case HIGH:
    		return 3;
    	case NORMAL:
    		return 2;
    	default:
    		return 1;
    	}
    	
    }

    @Override
    public void processFor(long interval, TimeUnit timeUnit) throws InterruptedException {
        /* Pseudocódigo:
         * while (!tempo_estourado) {
         *   t = escolha_tarefa();
         *   t.incCycles();
         *   this.converter.processFor(getQuantum(t.getPriority(), MILLISECONDS);
         * }
         */
        //TODO implementar
    }

    @Override
    public synchronized void close() throws Exception {
        /* - Libere quaisquer recursos alocados
         * - Cancele as tarefas não concluídas
         */
        //TODO implementar
    }
}
