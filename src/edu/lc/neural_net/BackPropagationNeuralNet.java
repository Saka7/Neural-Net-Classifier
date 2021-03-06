package edu.lc.neural_net;

import org.encog.Encog;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.mathutil.randomize.ConsistentRandomizer;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.persist.EncogDirectoryPersistence;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/** BackPropagation Neural Net Learning Algorithm */
public class BackPropagationNeuralNet implements NeuralNet {

  protected BasicNetwork network;
  protected MLDataSet trainingSet;
  private Backpropagation train; 
  protected List<Integer> iterations; 
  protected List<Double> errors; 
  protected double learningRate;

  @Override /** {@inheritDoc} */
  public void saveWeights(String filename) {
    if (!filename.substring(filename.length() - 3, filename.length()).equals(".eg"))
      filename += ".eg";
    EncogDirectoryPersistence.saveObject(new File(filename), network);
  }

  @Override /** {@inheritDoc} */
  public void loadWeights(String filename) {
    network = (BasicNetwork) EncogDirectoryPersistence.loadObject(new File(filename));
  }
  
  /** Neural Network structure initialization */
  public BackPropagationNeuralNet() {
    iterations = new ArrayList<>();
    errors = new ArrayList<>();
    
    network = new BasicNetwork();
    network.addLayer(new BasicLayer(null, true, 4));
    network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 10));
    network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 1));
    network.getStructure().finalizeStructure();
    network.reset();
    new ConsistentRandomizer(-1, 1, 500).randomize(network);
  }

  /** 
   * Loading neural network parameters initialization from file
   * @param filename 
  */
  public BackPropagationNeuralNet(String filename) {
    this();
    loadWeights(filename);
  }
  
  @Override /** {@inheritDoc} */
  public String train(final double[][] inputs, final double[][] expected, double learningRate, double maxError,
      long maxIterations) {

    this.learningRate = learningRate;
    trainingSet = new BasicMLDataSet(inputs, expected);
    train = new Backpropagation(network, trainingSet, this.learningRate, 0.3);
    train.fixFlatSpot(false);
    int epoch = 0;
    do {
      iterations.add(epoch);
      train.iteration();
      errors.add(train.getError());
      if (epoch++ > maxIterations) break;
    } while (train.getError() > maxError/100);
    return epoch + " " + train.getError();
  }

  @Override /** {@inheritDoc} */
  public double[] solve(final double[][] inputs) {
    double[] results = new double[inputs.length];
    trainingSet = new BasicMLDataSet(inputs, null);
    int i = 0;
    for (MLDataPair pair : trainingSet) {
      final MLData output = network.compute(pair.getInput());
      results[i++] = output.getData(0);
    }
    Encog.getInstance().shutdown();
    return results;
  }

  @Override /** {@inheritDoc} */
  public List<Integer> getIterations() {
    return iterations;
  }
 
  @Override /** {@inheritDoc} */
  public List<Double> getErrors() {
    return errors;
  }

  @Override /** {@inheritDoc} */
  public String getWeights() {
    return network.dumpWeights();
  }
}